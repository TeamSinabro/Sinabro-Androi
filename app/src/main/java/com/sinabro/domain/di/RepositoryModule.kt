package com.sinabro.domain.di

import com.sinabro.data.datasource.remote.pronounce.PronounceDataSource
import com.sinabro.data.datasource.remote.pronounce.PronounceSentenceDataSource
import com.sinabro.data.datasource.remote.qalearning.QALearningDataSource
import com.sinabro.data.datasource.remote.vocalearning.VocaLearningDataSource
import com.sinabro.data.datasource.remote.vocasearch.VocaSearchDataSource
import com.sinabro.data.repositoryimpl.pronounce.PronounceRepositoryImpl
import com.sinabro.data.repositoryimpl.pronounce.PronounceSentenceRepositoryImpl
import com.sinabro.data.repositoryimpl.qalearning.QALearningRepositoryImpl
import com.sinabro.data.repositoryimpl.vocalearning.VocaLearningRepositoryImpl
import com.sinabro.data.repositoryimpl.vocasearch.VocaSearchRepositoryImpl
import com.sinabro.domain.repository.pronounce.PronounceRepository
import com.sinabro.domain.repository.pronounce.PronounceSentenceRepository
import com.sinabro.domain.repository.qalearning.QALearningRepository
import com.sinabro.domain.repository.vocalearning.VocaLearningRepository
import com.sinabro.domain.repository.vocasearch.VocaSearchRepository
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

    @Singleton
    @Provides
    fun pronounceSentenceRepository(
        pronounceSentenceDataSource: PronounceSentenceDataSource
    ) : PronounceSentenceRepository{
        return PronounceSentenceRepositoryImpl(pronounceSentenceDataSource)
    }

    @Singleton
    @Provides
    fun vocaSearchRepository(
        vocaSearchDataSource: VocaSearchDataSource
    ) : VocaSearchRepository{
        return VocaSearchRepositoryImpl(vocaSearchDataSource)
    }

    @Singleton
    @Provides
    fun vocaLearningRepository(
        vocaLearningDataSource: VocaLearningDataSource
    ) : VocaLearningRepository{
        return VocaLearningRepositoryImpl(vocaLearningDataSource)
    }

    @Singleton
    @Provides
    fun QALearningRepository(
        qaLearningDataSource: QALearningDataSource
    ) : QALearningRepository{
        return QALearningRepositoryImpl(qaLearningDataSource)
    }
}