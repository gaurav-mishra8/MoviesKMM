package com.greenbot.cinema.android

import android.app.Application
import com.greenbot.cinema.android.di.appModule
import initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class CinemaApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@CinemaApp)
            modules(appModule)
        }
    }
}