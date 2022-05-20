package com.sinabro.domain.usecase.vocalearning

import com.sinabro.domain.model.response.vocalearning.VocaGetLearningData
import com.sinabro.domain.repository.vocalearning.VocaLearningRepository
import javax.inject.Inject

class GetVocaLearningDataUseCase @Inject constructor(
    private val repository : VocaLearningRepository
) {

    suspend operator fun invoke(publisher : String, subject : String, chapter : Int) : VocaGetLearningData{
        return repository.getVocaLearning(publisher, subject, chapter)
    }
}