package com.idz.colman2026class2.data.networking

import com.idz.colman2026class2.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesClient {

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("page") page: Int,
        @Query("include_adults") includeAdults: Boolean = false,
        @Query("include_videos") includeVideos: Boolean = true,
        @Query("language") language: String = "en"
    ): Call<Movies>
}