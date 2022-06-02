package com.sinabro.data.datasource.remote.qalearning

import com.sinabro.data.api.qalearning.QAService
import com.sinabro.data.model.response.qalearning.ResponseQAData
import javax.inject.Inject

class QALearningDataSourceImpl @Inject constructor(
    private val service : QAService
) : QALearningDataSource{

    override suspend fun getQAData(question: String): ResponseQAData {
        return service.getQAData(question)
    }
}