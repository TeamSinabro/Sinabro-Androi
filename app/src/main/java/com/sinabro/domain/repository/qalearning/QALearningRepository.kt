package com.sinabro.domain.repository.qalearning

import com.sinabro.domain.model.response.qalearning.QALearningData

interface QALearningRepository {

    suspend fun getQAData(question : String) : QALearningData
}