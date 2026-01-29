package com.idz.colman2026class2.data.repository.movies

import com.idz.colman2026class2.model.Movies

interface MoviesRepository {
    fun getTopRatedMovies(): Movies
}