package com.sinabro.shared.di

import android.app.Application
import androidx.databinding.ktx.BuildConfig
import com.sinabro.shared.util.SinabroDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SinaBroApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(SinabroDebugTree())
    }
}