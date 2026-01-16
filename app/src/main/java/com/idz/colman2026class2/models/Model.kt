package com.idz.colman2026class2.models

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import com.idz.colman2026class2.base.Completion
import com.idz.colman2026class2.base.StudentCompletion
import com.idz.colman2026class2.base.StudentsCompletion
import com.idz.colman2026class2.dao.AppLocalDB
import com.idz.colman2026class2.dao.AppLocalDbRepository
import com.idz.colman2026class2.models.StorageModel
import java.util.concurrent.Executors

class Model private constructor() {

    private val storageModel: StorageModel = StorageModel()
    private val firebaseModel = FirebaseModel()
    private val firebaseAuth = FirebaseAuthModel()

    companion object {
        val shared = Model()
    }

    fun getAllStudents(completion: StudentsCompletion) {
        firebaseModel.getAllStudents(completion)
    }

    fun addStudent(storageAPI: StorageModel.StorageAPI, profileImage: Bitmap ,student: Student, completion: Completion) {
        firebaseModel.addStudent(student) {
            storageModel.uploadStudentImage(storageAPI, profileImage, student) {
                imageUri ->
                if (!imageUri.isNullOrEmpty()) {
                    val studentCopy = student.copy(avatarUrlString = imageUri)
                    firebaseModel.addStudent(studentCopy, completion)
                } else {
                    completion()
                }
            }
        }
    }

    fun deleteStudent(student: Student) {
        firebaseModel.deleteStudent(student)
    }

    fun getStudentById(id: String, completion: StudentCompletion) {
        firebaseModel.getStudentById(id, completion)
    }
}

