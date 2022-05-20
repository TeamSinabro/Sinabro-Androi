package com.sinabro.data.datasource.remote.vocalearning

import com.sinabro.data.api.vocalearning.VocaLearningService
import com.sinabro.data.model.response.vocalearning.ResponseVocaLearningData
import javax.inject.Inject

class VocaLearningDataSourceImpl @Inject constructor(
    private val service : VocaLearningService
) : VocaLearningDataSource {
    override suspend fun getVocaLearning(
        publisher: String,
        subject: String,
        chapter: Int
    ): ResponseVocaLearningData {
        return service.getVocaLearning(publisher, subject, chapter)
    }
}