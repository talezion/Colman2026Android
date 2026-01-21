package com.idz.colman2026class2.models

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import com.idz.colman2026class2.base.Completion
import com.idz.colman2026class2.base.StudentCompletion
import com.idz.colman2026class2.base.StudentsCompletion
import com.idz.colman2026class2.dao.AppLocalDB
import com.idz.colman2026class2.dao.AppLocalDbRepository
import java.util.concurrent.Executors

class StudentsRepository private constructor() {

    private val storageModel: StorageModel = StorageModel()
    private val firebaseModel = FirebaseModel()
    private val firebaseAuth = FirebaseAuthModel()

    private val executor = Executors.newSingleThreadExecutor()
    private val mainHandler = Handler.createAsync(Looper.getMainLooper())
    private val database: AppLocalDbRepository = AppLocalDB.db

    private val students: LiveData<MutableList<Student>>? = null

    companion object Companion {
        val shared = StudentsRepository()
    }

    fun getAllStudents(): LiveData<MutableList<Student>> {
        return students ?: database.studentDao.getAllStudents()
    }

    fun refreshStudents() {
        val lastUpdated = Student.lastUpdated

        firebaseModel.getAllStudents(lastUpdated) {
            executor.execute {
                var time = lastUpdated
                for (student in it) {
                    database.studentDao.insertStudents(student)
                    student.lastUpdated?.let { studentLastUpdated ->
                        if (time < studentLastUpdated) {
                            time = studentLastUpdated
                        }
                    }
                    Student.lastUpdated = time
                }
            }

        }
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

