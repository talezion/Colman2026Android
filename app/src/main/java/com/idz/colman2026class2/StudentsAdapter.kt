package com.idz.colman2026class2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.idz.colman2026class2.databinding.StudentRowLayoutBinding
import com.idz.colman2026class2.models.Student

class StudentsAdapter(
    private var students: List<Student>,
): RecyclerView.Adapter<StudentRowViewHolder>() {

    override fun getItemCount(): Int = students.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentRowViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = StudentRowLayoutBinding.inflate(inflator, parent, false)
        return StudentRowViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: StudentRowViewHolder, position: Int) {
        holder.bind(students[position], position)
    }
}