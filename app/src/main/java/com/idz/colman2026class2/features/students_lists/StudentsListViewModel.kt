package com.idz.colman2026class2.features.students_lists

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.idz.colman2026class2.models.Student
import com.idz.colman2026class2.models.StudentsRepository

class StudentsListViewModel: ViewModel() {

    val data: LiveData<MutableList<Student>> = StudentsRepository.shared.getAllStudents()

    fun refreshStudents() {
        StudentsRepository.shared.refreshStudents()
    }
}