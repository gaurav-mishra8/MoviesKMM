package com.greenbot.movieskmm.cache

import app.cash.sqldelight.db.SqlDriver
import com.greenbot.movieskmm.AppDatabase
import com.greenbot.movieskmm.entity.Movie

internal class Database(driver: SqlDriver) {
    private val database = AppDatabase(driver)
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllMovies()
        }
    }

    internal fun getAllMovies(): List<Movie> {
        return dbQuery.selectAllMovies(::mapEntity).executeAsList()
    }

    private fun mapEntity(id: Long, title: String?, overview: String?): Movie {
        return Movie(
            id = id.toInt(),
            title = title ?: "",
            overview = overview ?: ""
        )
    }

    internal fun insertMovies(movie: List<Movie>) {
        dbQuery.transaction {
            movie.forEach {
                insertMovie(it)
            }
        }
    }

    private fun insertMovie(movie: Movie) {
        dbQuery.insertMovie(
            id = movie.id.toLong(),
            title = movie.title,
            overview = movie.overview
        )
    }
}