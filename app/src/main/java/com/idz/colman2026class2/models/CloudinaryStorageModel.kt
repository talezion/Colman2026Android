package com.idz.colman2026class2.models

import android.content.Context
import android.graphics.Bitmap
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.policy.GlobalUploadPolicy
import com.cloudinary.android.policy.UploadPolicy
import com.idz.colman2026class2.MyApplication
import com.idz.colman2026class2.base.StringCompletion
import java.io.File

class CloudinaryStorageModel {

    init {
        val config = mapOf(
            "cloud_name" to "duutna6lt",
            "api_key" to "646421153716863",
            "api_secret" to "zbfAoiCiwRSW0_Hj39GzHerfnbU"
        )

        MyApplication.appContext?.let {
            MediaManager.init(it, config)
            MediaManager.get().globalUploadPolicy = GlobalUploadPolicy.Builder()
                .maxConcurrentRequests(3)
                .networkPolicy(UploadPolicy.NetworkType.UNMETERED)
                .build()
        }
    }
    
    fun uploadStudentImage(image: Bitmap, student: Student, completion: StringCompletion) {

        val context = MyApplication.appContext ?: return
        val file = bitmapToFile(image, context)

        MediaManager.get().upload(file.path)
            .option("images", "students/${student.id}/profile_image")
            .callback(object : com.cloudinary.android.callback.UploadCallback {
                override fun onStart(requestId: String) {
                    // Upload started
                }

                override fun onProgress(requestId: String, bytes: Long, totalBytes: Long) {
                    // Upload progress
                }

                override fun onSuccess(requestId: String, resultData: Map<*, *>) {
                    val url = resultData["secure_url"] as? String
                    completion(url)
                }

                override fun onError(requestId: String, error: ErrorInfo) {
                    completion(null)
                }

                override fun onReschedule(requestId: String, error: com.cloudinary.android.callback.ErrorInfo) {
                    // Upload rescheduled
                }
            })
            .dispatch()
    }

    private fun bitmapToFile(image: Bitmap, context: Context): File {
        val file = File(context.cacheDir, "temp_image_${System.currentTimeMillis()}.jpg")
        file.outputStream().use {
            image.compress(Bitmap.CompressFormat.JPEG, 100, it)
            it.flush()
        }
        return file
    }
}