package com.idz.colman2026class2.features.students_lists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.idz.colman2026class2.databinding.FragmentStudentsListBinding
import com.idz.colman2026class2.model.Student

class StudentsListFragment : Fragment() {

    private var binding: FragmentStudentsListBinding? = null
    private val viewModel: StudentsListViewModel by viewModels()
    private var adapter: StudentsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentsListBinding.inflate(layoutInflater, container, false)
        setupRecyclerView()
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun setupRecyclerView() {
        val layout = LinearLayoutManager(context)
        binding?.recyclerView?.layoutManager = layout
        binding?.recyclerView?.setHasFixedSize(true)

//        binding?.progressBar?.visibility = View.VISIBLE

        adapter = StudentsAdapter(viewModel.data.value)
        adapter?.listener = object : OnItemClickListener {

            override fun onStudentItemClick(student: Student) {
                navigateToPinkFragment(student)
            }
        }
//        binding?.progressBar?.visibility = View.GONE
        binding?.recyclerView?.adapter = adapter

        binding?.swipeRefresh?.setOnRefreshListener {
            binding?.swipeRefresh?.isRefreshing = true
            refreshData()
        }

        observeStudents()
    }

    private fun observeStudents() {
        fetchMovies()

        return
        viewModel.data.observe(viewLifecycleOwner) {
            adapter?.students = it
            adapter?.notifyDataSetChanged()
//            binding?.progressBar?.visibility = View.GONE
            binding?.swipeRefresh?.isRefreshing = false
        }
    }

    private fun fetchMovies() {
        viewModel.getMovies { movies ->

            adapter?.students = movies.results?.map { movie ->
                Student(
                    id = movie.title.toString(),
                    name = movie.title ?: "Unknown",
                    isPresent = false,
                    avatarUrlString = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                    lastUpdated = System.currentTimeMillis()
                )
            }?.toMutableList()

            adapter?.notifyDataSetChanged()
//            binding?.progressBar?.visibility = View.GONE
            binding?.swipeRefresh?.isRefreshing = false
        }
    }

    private fun refreshData() {
//        binding?.progressBar?.visibility = View.VISIBLE
        viewModel.refreshStudents()
    }

    private fun navigateToPinkFragment(student: Student) {
        view?.let {
            val action = StudentsListFragmentDirections.actionStudentsListFragmentToBlueFragment(student.name)
            Navigation.findNavController(it).navigate(action)
        }
    }
}