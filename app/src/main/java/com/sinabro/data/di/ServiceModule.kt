package com.sinabro.data.di

import com.sinabro.data.api.pronounce.PronounceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {


    @Singleton
    @Provides
    fun pronounceService(retrofit: Retrofit) : PronounceService {
        return retrofit.create(PronounceService::class.java)
    }
}