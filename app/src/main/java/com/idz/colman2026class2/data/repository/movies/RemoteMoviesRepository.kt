package com.idz.colman2026class2.data.repository.movies

import android.util.Log
import com.idz.colman2026class2.data.networking.NetworkClient
import com.idz.colman2026class2.model.Movies

class RemoteMoviesRepository: MoviesRepository {

    companion object {
        val shared = RemoteMoviesRepository()
    }

    override fun getTopRatedMovies(): Movies {
        val request = NetworkClient.moviesApiClient.getTopRatedMovies(page = 1)
        Log.i("TAG", "getTopRatedMovies: request: $request")

        val response = request.execute()

        val movies =  when (response.isSuccessful) {
            true -> {
                response.body() ?: run {
                    Movies(
                        page = null,
                        results = mutableListOf(),
                        totalResults = null,
                        totalPages = null
                    )
                }
            }
            false -> {
                Log.i("TAG", "getTopRatedMovies: failed ${response.message()}, code: ${response.code()}, errorBody: ${response.errorBody()?.string()}")
                Movies(
                    page = null,
                    results = mutableListOf(),
                    totalResults = null,
                    totalPages = null
                )
            }
        }

        return movies
    }
}