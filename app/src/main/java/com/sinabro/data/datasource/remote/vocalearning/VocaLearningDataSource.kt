package com.sinabro.data.datasource.remote.vocalearning

import com.sinabro.data.model.response.vocalearning.ResponseVocaLearningData

interface VocaLearningDataSource {

    suspend fun getVocaLearning(publisher : String, subject : String, chapter : Int) : ResponseVocaLearningData
}