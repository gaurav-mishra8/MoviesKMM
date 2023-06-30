package com.greenbot.cinema

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual fun platformModule() = module {
    single<SqlDriver> {
        val driver = NativeSqliteDriver(AppDatabase.Schema, "test.db")
        driver
    }
    single { Darwin.create() }
}