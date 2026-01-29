package com.idz.colman2026class2.data.models

import android.graphics.Bitmap
import com.idz.colman2026class2.base.StringCompletion
import com.idz.colman2026class2.model.Student


class StorageModel {

    enum class StorageAPI {
        FIREBASE,
        CLOUDINARY
    }

    private val firebaseStorage = FirebaseStorageModel()
    private val cloudinaryStorage = CloudinaryStorageModel()

    fun uploadStudentImage(api: StorageAPI, image: Bitmap, student: Student, completion: StringCompletion) {
        when (api) {
            StorageAPI.FIREBASE -> firebaseStorage.uploadStudentImage(image, student, completion)
            StorageAPI.CLOUDINARY -> cloudinaryStorage.uploadStudentImage(image, student, completion)
        }
    }
}
