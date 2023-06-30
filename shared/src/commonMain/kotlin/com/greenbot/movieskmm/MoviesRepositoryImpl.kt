package com.greenbot.movieskmm

import app.cash.sqldelight.db.SqlDriver
import com.greenbot.movieskmm.cache.Database
import com.greenbot.movieskmm.entity.Movie
import com.greenbot.movieskmm.network.MoviesApi
import org.koin.core.component.KoinComponent

interface MoviesRepository {
    suspend fun getPopularMovies(forceLoad: Boolean): List<Movie>
}

class MoviesRepositoryImpl(
    private val api: MoviesApi,
    sqlDriver: SqlDriver
) : KoinComponent,
    MoviesRepository {
    private val database = Database(sqlDriver)

    override suspend fun getPopularMovies(forceLoad: Boolean): List<Movie> {
        val cachedMovies = database.getAllMovies()
        return if (cachedMovies.isEmpty() || forceLoad) {
            api.getAllMovies().also {
                database.clearDatabase()
                database.insertMovies(it)
            }
        } else {
            cachedMovies
        }
    }
}