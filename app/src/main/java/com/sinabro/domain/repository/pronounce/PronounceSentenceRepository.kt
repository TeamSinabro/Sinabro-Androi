package com.sinabro.domain.repository.pronounce

import com.sinabro.domain.model.response.pronounce.PronounceGetSentenceData

interface PronounceSentenceRepository {

    suspend fun getPronunciationSentence(publisher : String, subject : String, chapter : Int) : PronounceGetSentenceData
}