package com.idz.colman2026class2.models

class Model private constructor() {

    val students = mutableListOf<Student>()

    init {
        for (i in 1..20) {
            val student = Student(
                name = "Student $i",
                id = "ID${1000 + i}",
                avatarUrlString = "https://i.pravatar.cc/150?img=$i",
                isPresent = false
            )
            students.add(student)
        }
    }
    companion object {
        val shared = Model()
    }
}