package com.greenbot.movieskmm

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import io.ktor.client.engine.android.Android
import org.koin.dsl.module

actual fun platformModule() = module {
    single<SqlDriver> {
        val driver = AndroidSqliteDriver(AppDatabase.Schema, get(), "test.db")
        driver
    }
    single {
        Android.create()
    }
}