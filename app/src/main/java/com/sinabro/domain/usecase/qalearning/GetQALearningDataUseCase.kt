package com.sinabro.domain.usecase.qalearning

import com.sinabro.domain.model.response.qalearning.QALearningData
import com.sinabro.domain.repository.qalearning.QALearningRepository
import javax.inject.Inject

class GetQALearningDataUseCase @Inject constructor(
    private val repository : QALearningRepository
) {

    suspend operator fun invoke(question : String) : QALearningData{
       return repository.getQAData(question)
    }
}