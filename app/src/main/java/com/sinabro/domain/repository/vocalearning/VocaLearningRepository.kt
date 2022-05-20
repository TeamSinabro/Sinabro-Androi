package com.sinabro.domain.repository.vocalearning

import com.sinabro.domain.model.response.vocalearning.VocaGetLearningData

interface VocaLearningRepository {

    suspend fun getVocaLearning(publisher : String, subject : String, chapter : Int) : VocaGetLearningData
}