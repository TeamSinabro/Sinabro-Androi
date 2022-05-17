package com.sinabro.domain.usecase

import com.sinabro.domain.model.response.pronounce.PronounceGetSentenceData
import com.sinabro.domain.repository.pronounce.PronounceSentenceRepository
import javax.inject.Inject

class GetPronounceSentenceDataUseCase @Inject constructor(
    val repository : PronounceSentenceRepository
)  {
    suspend operator fun invoke(publisher : String, subject : String, chapter : Int) : PronounceGetSentenceData {
        return repository.getPronunciationSentence(publisher, subject, chapter)
    }
}