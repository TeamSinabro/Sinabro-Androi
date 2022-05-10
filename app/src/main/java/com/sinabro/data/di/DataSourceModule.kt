package com.sinabro.data.di

import com.sinabro.data.api.pronounce.PronounceService
import com.sinabro.data.datasource.remote.pronounce.PronounceDataSource
import com.sinabro.data.datasource.remote.pronounce.PronounceDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun pronounceDataSource(
        pronounceService: PronounceService
    ) : PronounceDataSource{
        return PronounceDataSourceImpl(pronounceService)
    }


}