package com.sinabro.shared.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SinaBroApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}