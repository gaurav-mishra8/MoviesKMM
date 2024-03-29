package com.greenbot.movieskmm

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual fun platformModule() = module {
    // local db
    single<SqlDriver> {
        val driver = NativeSqliteDriver(AppDatabase.Schema, "test.db")
        driver
    }

    // network client
    single { Darwin.create() }
}