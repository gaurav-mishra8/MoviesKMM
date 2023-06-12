package com.greenbot.cinema.network

import com.greenbot.cinema.entity.MotionPicture
import com.greenbot.cinema.entity.MotionPictureResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class MoviesApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun getAllMovies(): List<MotionPicture> {
        val response =
            httpClient.get("https://api.themoviedb.org/3/movie/popular?api_key=b080301b00b10afb254681c4ee7d6ba8")
                .body<MotionPictureResponse>()
        return response.results
    }
}