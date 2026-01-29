package com.idz.colman2026class2.base

import com.idz.colman2026class2.model.Student

typealias StudentsCompletion = (List<Student>) -> Unit
typealias StudentCompletion = (Student) -> Unit
typealias Completion = () -> Unit
typealias StringCompletion = (String?) -> Unit