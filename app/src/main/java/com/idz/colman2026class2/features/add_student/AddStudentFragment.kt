package com.idz.colman2026class2.features.add_student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.idz.colman2026class2.databinding.FragmentAddStudentBinding
import com.idz.colman2026class2.models.StudentsRepository
import com.idz.colman2026class2.models.StorageModel
import com.idz.colman2026class2.models.Student
import com.idz.colman2026class2.utilis.extensions.bitmap

class AddStudentFragment : Fragment() {

    private var cameraLauncher: ActivityResultLauncher<Void?>? = null

    private var binding: FragmentAddStudentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddStudentBinding.inflate(layoutInflater, container, false)
        setupView()

        cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            bitmap ->
            bitmap?.let {
                binding?.avatarImageView?.setImageBitmap(it)
            } ?: Toast.makeText(context, "No image captured", Toast.LENGTH_SHORT).show()
        }

        binding?.takePictureButton?.setOnClickListener {
            cameraLauncher?.launch(null)
        }

        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    private fun setupView() {

        binding?.loadingIndicator?.visibility = View.GONE

        binding?.cancelButton?.setOnClickListener {
            dismiss()
        }

        binding?.saveStudentButton?.setOnClickListener {

            binding?.loadingIndicator?.visibility = View.VISIBLE

            val studentName: String = binding?.nameEditText?.text.toString()
            val studentId: String = binding?.idEditText?.text.toString()

            val student = Student(
                id = studentId,
                name = studentName,
                isPresent = false,
                avatarUrlString = null,
                lastUpdated = null
            )

            binding?.avatarImageView?.isDrawingCacheEnabled = true
            binding?.avatarImageView?.buildDrawingCache()
            val bitmap = binding?.avatarImageView?.bitmap

            bitmap?.let {
                StudentsRepository.Companion.shared.addStudent(
                    storageAPI = StorageModel.StorageAPI.CLOUDINARY,
                    profileImage = it,
                    student = student
                ) {
                    dismiss()
                }
            } ?: run {
                Toast.makeText(context, "Please capture an image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun dismiss() {
        view?.findNavController()?.popBackStack()
    }
}