package com.idz.colman2026class2.model

import kotlinx.serialization.SerialName

data class Movies(
    val page: Int?,
    val results: List<Movie>?,

    @SerialName("total_results")
    val totalResults: Int?,

    @SerialName("total_pages")
    val totalPages: Int?
)
