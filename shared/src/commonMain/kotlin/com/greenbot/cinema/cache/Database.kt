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

    private fun mapToMotionPicture(id: String, title: String?, overview: String?): MotionPicture {
        return MotionPicture(
            id = id,
            title = title ?: "",
            overview = overview ?: ""
        )
    }
}