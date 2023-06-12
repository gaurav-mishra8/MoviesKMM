package com.greenbot.cinema.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MotionPictureResponse(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<MotionPicture>,
    @SerialName("total_pages")
    val totalPages: Int
)

@Serializable
data class MotionPicture(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("overview")
    val overview: String
)