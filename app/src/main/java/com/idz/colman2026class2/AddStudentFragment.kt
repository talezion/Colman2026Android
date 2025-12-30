package com.idz.colman2026class2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.idz.colman2026class2.databinding.FragmentAddStudentBinding
import kotlin.text.clear

class AddStudentFragment : Fragment() {

    private var binding: FragmentAddStudentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddStudentBinding.inflate(layoutInflater, container, false)
        setupView()
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    private fun setupView() {
        binding?.cancelButton?.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        binding?.saveStudentButton?.setOnClickListener {
            val studentName: String = binding?.nameEditText?.text.toString()
            val studentId: String = binding?.idEditText?.text.toString()

            binding?.statusTextView?.text = "Student Saved: Name = $studentName, ID = $studentId"
            binding?.nameEditText?.text?.clear()
            binding?.idEditText?.text?.clear()
        }
    }
}