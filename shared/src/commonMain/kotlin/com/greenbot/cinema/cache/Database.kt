package com.greenbot.cinema.cache

import com.greenbot.cinema.AppDatabase
import com.greenbot.cinema.entity.MotionPicture

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllMovies()
        }
    }

    internal fun getAllMovies(): List<MotionPicture> {
        return dbQuery.selectAllMovies(::mapToMotionPicture).executeAsList()
    }

    private fun mapToMotionPicture(id: Long, title: String?, overview: String?): MotionPicture {
        return MotionPicture(
            id = id.toInt(),
            title = title ?: "",
            overview = overview ?: ""
        )
    }

    internal fun insertMovies(motionPicture: List<MotionPicture>) {
        dbQuery.transaction {
            motionPicture.forEach {
                insertMotionPicture(it)
            }
        }
    }

    private fun insertMotionPicture(motionPicture: MotionPicture) {
        dbQuery.insertMovie(
            id = motionPicture.id.toLong(),
            title = motionPicture.title,
            overview = motionPicture.overview
        )
    }
}