package com.greenbot.movieskmm.network

import com.greenbot.movieskmm.entity.Movie
import com.greenbot.movieskmm.entity.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.component.KoinComponent

class MoviesApi(private val httpClient: HttpClient) : KoinComponent {

    suspend fun getPopularMovies(): List<Movie> {
        val response =
            httpClient.get("${BASE_URL}movie/popular?api_key=$API_KEY")
                .body<MovieResponse>()
        return response.results
    }

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "b080301b00b10afb254681c4ee7d6ba8"
    }
}