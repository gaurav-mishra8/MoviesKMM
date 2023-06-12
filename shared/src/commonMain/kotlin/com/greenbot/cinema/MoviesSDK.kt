package com.greenbot.cinema

import com.greenbot.cinema.cache.Database
import com.greenbot.cinema.cache.DatabaseDriverFactory
import com.greenbot.cinema.entity.MotionPicture
import com.greenbot.cinema.network.MoviesApi

class MoviesSDK (databseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databseDriverFactory)
    private val api = MoviesApi()

    suspend fun getPopularMovies(forceLoad: Boolean): List<MotionPicture> {
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