package com.sinabro.data.repositoryimpl.vocalearning

import com.sinabro.data.datasource.remote.vocalearning.VocaLearningDataSource
import com.sinabro.data.mapper.VocaLearningMapper
import com.sinabro.domain.model.response.vocalearning.VocaGetLearningData
import com.sinabro.domain.repository.vocalearning.VocaLearningRepository
import javax.inject.Inject

class VocaLearningRepositoryImpl @Inject constructor(
    private val dataSource : VocaLearningDataSource
) : VocaLearningRepository {
    override suspend fun getVocaLearning(
        publisher: String,
        subject: String,
        chapter: Int
    ): VocaGetLearningData {
        return VocaLearningMapper.mapperToVocaLearning(dataSource.getVocaLearning(publisher, subject, chapter))
    }
}