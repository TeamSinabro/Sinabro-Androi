package com.sinabro.data.datasource.remote.qalearning

import com.sinabro.data.model.response.qalearning.ResponseQAData

interface QALearningDataSource {

    suspend fun getQAData(question : String) : ResponseQAData
}