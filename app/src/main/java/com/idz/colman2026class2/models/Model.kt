package com.idz.colman2026class2.models

import android.os.Handler
import android.os.Looper
import com.idz.colman2026class2.base.Completion
import com.idz.colman2026class2.base.StudentCompletion
import com.idz.colman2026class2.base.StudentsCompletion
import com.idz.colman2026class2.dao.AppLocalDB
import com.idz.colman2026class2.dao.AppLocalDbRepository
import java.util.concurrent.Executors

class Model private constructor() {

    private val executor = Executors.newSingleThreadExecutor()
    private val mainHandler = Handler.createAsync(Looper.getMainLooper())

    private val database: AppLocalDbRepository = AppLocalDB.db

    companion object {
        val shared = Model()
    }

    fun getAllStudents(completion: StudentsCompletion) {
        executor.execute {
            val students = database.studentDao.getAllStudents()
            mainHandler.post {
                completion(students)
            }
        }
    }

    fun addStudent(student: Student, completion: Completion) {
        executor.execute {
            database.studentDao.insertStudents(student)
            mainHandler.post {
                completion()
            }
        }
    }

    fun deleteStudent(student: Student) {
        database.studentDao.deleteStudent(student)
    }

    fun getStudentById(id: String, completion: StudentCompletion) {
        executor.execute {
            val student = database.studentDao.getStudentById(id)
            mainHandler.post {
                completion(student)
            }
        }
    }
}

