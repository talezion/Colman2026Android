package com.idz.colman2026class2.features.students_lists

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idz.colman2026class2.data.repository.movies.RemoteMoviesRepository
import com.idz.colman2026class2.model.Student
import com.idz.colman2026class2.data.repository.students.StudentsRepository
import com.idz.colman2026class2.model.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentsListViewModel: ViewModel() {

    val data: LiveData<MutableList<Student>> = StudentsRepository.shared.getAllStudents()

    fun refreshStudents() {
        StudentsRepository.shared.refreshStudents()
    }

    fun getMovies(callback: (Movies) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = RemoteMoviesRepository.shared.getTopRatedMovies()
            Log.i("TAG", "Movies: ${movies.results?.size}")

            withContext(Dispatchers.Main) {
                callback(movies)
            }
        }
    }
}