package com.idz.colman2026class2.data.models

import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.firestore
import com.idz.colman2026class2.base.Completion
import com.idz.colman2026class2.base.StudentCompletion
import com.idz.colman2026class2.base.StudentsCompletion
import com.idz.colman2026class2.model.Student

class FirebaseModel {

    private val db = Firebase.firestore

    private companion object COLLECTIONS {
        const val STUDENTS = "students"
    }

    fun getAllStudents(since: Long, completion: StudentsCompletion) {
        db.collection(STUDENTS)
            .whereGreaterThanOrEqualTo(Student.LAST_UPDATED_KEY, Timestamp(since / 1000, 0))
            .get()
            .addOnCompleteListener { result ->
                when (result.isSuccessful) {
                    true -> completion(result.result.map { Student.fromJson(it.data) })
                    false -> completion(emptyList())
                }
            }
    }

    fun addStudent(student: Student, completion: Completion) {
        db.collection(STUDENTS)
            .document(student.id)
            .set(student.toJson)
            .addOnSuccessListener { documentReference ->
                completion()
            }
            .addOnFailureListener { e ->
                completion()
            }

    }

    fun deleteStudent(student: Student) {

    }

    fun getStudentById(id: String, completion: StudentCompletion) {

    }
}
