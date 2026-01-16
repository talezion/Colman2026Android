package com.idz.colman2026class2

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.idz.colman2026class2.databinding.FragmentAddStudentBinding
import com.idz.colman2026class2.models.Model
import com.idz.colman2026class2.models.StorageModel
import com.idz.colman2026class2.models.Student
import com.idz.colman2026class2.utilis.extensions.bitmap
import kotlin.text.clear

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
                name = studentName,
                id = studentId,
                isPresent = false,
                avatarUrlString = null
            )

            binding?.avatarImageView?.isDrawingCacheEnabled = true
            binding?.avatarImageView?.buildDrawingCache()
            val bitmap = binding?.avatarImageView?.bitmap

            bitmap?.let {
                Model.shared.addStudent(
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