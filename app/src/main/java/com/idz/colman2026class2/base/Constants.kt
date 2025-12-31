package com.idz.colman2026class2.base

import com.idz.colman2026class2.models.Student

typealias StudentsCompletion = (List<Student>) -> Unit
typealias StudentCompletion = (Student) -> Unit
typealias Completion = () -> Unit