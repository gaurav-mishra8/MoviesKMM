package com.greenbot.cinema

import app.cash.sqldelight.db.SqlDriver
import com.greenbot.cinema.cache.Database
import com.greenbot.cinema.entity.MotionPicture
import com.greenbot.cinema.network.MoviesApi
import org.koin.core.component.KoinComponent

interface MoviesRepository {
    suspend fun getPopularMovies(forceLoad: Boolean): List<MotionPicture>
}

class MoviesRepositoryImpl(
    private val api: MoviesApi,
    sqlDriver: SqlDriver
) : KoinComponent,
    MoviesRepository {
    private val database = Database(sqlDriver)

    override suspend fun getPopularMovies(forceLoad: Boolean): List<MotionPicture> {
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