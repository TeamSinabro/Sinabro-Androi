package com.sinabro.data.datasource.remote.pronounce

import com.sinabro.data.model.response.pronounce.ResponsePronounceSentenceData

interface PronounceSentenceDataSource {

    suspend fun getPronunciationSentence(publisher : String, subject : String, chapter : Int) : ResponsePronounceSentenceData
}