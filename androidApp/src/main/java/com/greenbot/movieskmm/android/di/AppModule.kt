package com.greenbot.movieskmm.android.di

import com.greenbot.movieskmm.android.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainViewModel)
}