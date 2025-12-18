package com.idz.colman2026class2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.idz.colman2026class2.databinding.FragmentStudentsListBinding
import com.idz.colman2026class2.models.Model
import com.idz.colman2026class2.models.Student

class StudentsListFragment : Fragment() {

    private var binding: FragmentStudentsListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentsListBinding.inflate(layoutInflater, container, false)
        setupRecyclerView()
        return binding?.root
    }

    private fun setupRecyclerView() {
        val layout = LinearLayoutManager(context)
        binding?.recyclerView?.layoutManager = layout
        binding?.recyclerView?.setHasFixedSize(true)

        val adapter = StudentsAdapter(Model.shared.students)
        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val student = Model.shared.students[position]
                presentToastFor(student)
            }

            override fun onStudentItemClick(student: Student) {
                presentToastFor(student)
            }
        }
        binding?.recyclerView?.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            StudentsListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private fun presentToastFor(student: Student) {
        val presentStatus = if (student.isPresent) "present" else "absent"

        Toast.makeText(context,
            "${student.name} is $presentStatus",
            Toast.LENGTH_SHORT
        ).show()
    }
}