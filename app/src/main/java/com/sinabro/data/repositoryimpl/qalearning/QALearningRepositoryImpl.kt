package com.sinabro.data.repositoryimpl.qalearning

import com.sinabro.data.datasource.remote.qalearning.QALearningDataSource
import com.sinabro.data.mapper.QALearningMapper
import com.sinabro.domain.model.response.qalearning.QALearningData
import com.sinabro.domain.repository.qalearning.QALearningRepository
import javax.inject.Inject

class QALearningRepositoryImpl @Inject  constructor(
    private val dataSource : QALearningDataSource
) : QALearningRepository {
    override suspend fun getQAData(question: String): QALearningData {
        return QALearningMapper.mapperToQALearning(dataSource.getQAData(question))
    }
}