package com.sinabro.data.mapper

import com.sinabro.data.model.response.qalearning.ResponseQAData
import com.sinabro.domain.model.response.qalearning.QALearningData

object QALearningMapper {

    fun mapperToQALearning(responseQAData: ResponseQAData) : QALearningData{
        return QALearningData(responseQAData.answer)
    }
}