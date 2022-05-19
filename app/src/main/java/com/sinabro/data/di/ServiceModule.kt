package com.sinabro.data.di

import com.sinabro.data.api.*
import com.sinabro.data.api.pronounce.PronounceSentenceService
import com.sinabro.data.api.pronounce.PronounceService
import com.sinabro.data.api.vocasearch.VocaSearchService
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
    fun pronounceService(@NetworkModule.PronounceBaseRetrofit retrofit: Retrofit) : PronounceService {
        return retrofit.create(PronounceService::class.java)
    }

    @Singleton
    @Provides
    fun pronounceSentenceService(@NetworkModule.SinabroBaseRetrofit retrofit: Retrofit) : PronounceSentenceService {
        return retrofit.create(PronounceSentenceService::class.java)
    }

    @Singleton
    @Provides
    fun vocaSearchService(@NetworkModule.SinabroBaseRetrofit retrofit : Retrofit) : VocaSearchService{
        return retrofit.create(VocaSearchService::class.java)
    }
}