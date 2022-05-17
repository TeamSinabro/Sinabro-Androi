package com.sinabro.data.di

import com.sinabro.data.api.pronounce.PronounceSentenceService
import com.sinabro.data.api.pronounce.PronounceService
import com.sinabro.data.api.vocasearch.VocaSearchService
import com.sinabro.data.datasource.remote.pronounce.PronounceDataSource
import com.sinabro.data.datasource.remote.pronounce.PronounceDataSourceImpl
import com.sinabro.data.datasource.remote.pronounce.PronounceSentenceDataImpl
import com.sinabro.data.datasource.remote.pronounce.PronounceSentenceDataSource
import com.sinabro.data.datasource.remote.vocasearch.VocaSearchDataSource
import com.sinabro.data.datasource.remote.vocasearch.VocaSearchDataSourceImpl
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

    @Singleton
    @Provides
    fun pronounceSentenceDataSource(
        pronounceSentenceService : PronounceSentenceService
    ) : PronounceSentenceDataSource{
        return PronounceSentenceDataImpl(pronounceSentenceService)
    }

    @Singleton
    @Provides
    fun vocaSearchDataSource(
        vocaSearchService : VocaSearchService
    ) : VocaSearchDataSource{
        return VocaSearchDataSourceImpl(vocaSearchService)
    }
}