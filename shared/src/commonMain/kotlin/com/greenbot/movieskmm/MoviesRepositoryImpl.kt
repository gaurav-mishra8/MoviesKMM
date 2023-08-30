package com.greenbot.movieskmm

import com.greenbot.movieskmm.cache.Database
import com.greenbot.movieskmm.cache.DatabaseDriverFactory
import com.greenbot.movieskmm.entity.Movie
import com.greenbot.movieskmm.network.MoviesApi
import kotlinx.coroutines.delay

interface MoviesRepository {
    suspend fun getPopularMovies(forceLoad: Boolean): List<Movie>
}

class MoviesRepositoryImpl(
    databaseDriverFactory: DatabaseDriverFactory
) : MoviesRepository {
    private val database = Database(databaseDriverFactory.createDriver())
    private val api = MoviesApi()

    override suspend fun getPopularMovies(forceLoad: Boolean): List<Movie> {
        val cachedMovies = database.getAllMovies()
        return if (cachedMovies.isEmpty() || forceLoad) {
            api.getPopularMovies().also {
                database.clearDatabase()
                database.insertMovies(it)
            }
        } else {
            delay(1000)
            cachedMovies
        }
    }
}