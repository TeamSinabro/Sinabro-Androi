package com.sinabro.data.mapper

import com.sinabro.data.model.response.vocalearning.ResponseVocaLearningData
import com.sinabro.domain.model.response.vocalearning.VocaGetLearningData

object VocaLearningMapper {

    fun mapperToVocaLearning(responseVocaLearningData: ResponseVocaLearningData) : VocaGetLearningData{
        return VocaGetLearningData(
            answer = responseVocaLearningData.data.answer,
            answerSource = responseVocaLearningData.data.answerSource,
        optionList = responseVocaLearningData.data.optionList,
        problem = responseVocaLearningData.data.problem,
        vocaDefinition = responseVocaLearningData.data.vocaDefinition
        )

    }
}