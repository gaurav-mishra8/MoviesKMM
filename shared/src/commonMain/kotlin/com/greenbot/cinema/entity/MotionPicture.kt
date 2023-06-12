package com.greenbot.cinema.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MotionPicture(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("overview")
    val overview: String
)