package com.sinabro.domain.di

import com.sinabro.data.datasource.remote.pronounce.PronounceDataSource
import com.sinabro.data.repositoryimpl.pronounce.PronounceRepositoryImpl
import com.sinabro.domain.repository.pronounce.PronounceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun pronounceRepository(
        pronounceDataSource: PronounceDataSource
    ) : PronounceRepository{
        return PronounceRepositoryImpl(pronounceDataSource)
    }

}