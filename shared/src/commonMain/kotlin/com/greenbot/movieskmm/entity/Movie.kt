package com.greenbot.movieskmm.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<Movie>,
    @SerialName("total_pages")
    val totalPages: Int
)

@Serializable
data class Movie(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    private val posterPath: String,
    @SerialName("backdrop_path")
    private val backDropPath: String
) {
    val img
        get() = "$IMAGE_BASE_URL/$posterPath"
    val backgroundImg
        get() = "$IMAGE_BASE_URL/$backDropPath"
}

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"