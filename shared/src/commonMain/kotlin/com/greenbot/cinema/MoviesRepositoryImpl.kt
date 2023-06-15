package com.greenbot.cinema

import com.greenbot.cinema.cache.Database
import com.greenbot.cinema.cache.DatabaseDriverFactory
import com.greenbot.cinema.entity.MotionPicture
import com.greenbot.cinema.network.MoviesApi

interface MoviesRepository {
    suspend fun getPopularMovies(forceLoad: Boolean): List<MotionPicture>
}

class MoviesRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory) : MoviesRepository {
    private val database = Database(databaseDriverFactory)
    private val api = MoviesApi()

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