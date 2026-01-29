package com.idz.colman2026class2.data.networking

import com.idz.colman2026class2.model.Movie
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesAuthInterceptor : Interceptor {

    companion object {
        private const val YOUR_API_KEY = "ACCESS_TOKEN"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $YOUR_API_KEY")
            .addHeader("accept", "application/json")
            .build()

        return chain.proceed(request)
    }
}

object NetworkClient {

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(MoviesAuthInterceptor())
            .build()
    }

    val moviesApiClient: MoviesClient by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MoviesClient::class.java)
    }
}