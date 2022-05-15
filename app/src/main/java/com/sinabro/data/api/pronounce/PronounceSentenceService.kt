package com.sinabro.data.api.pronounce

import com.sinabro.data.model.response.ResponsePronounceSentenceData
import retrofit2.http.GET
import retrofit2.http.Query

interface PronounceSentenceService {

    @GET("/voca/pronunciation")
    suspend fun getPronunciationSentence(
        @Query("publisher") publisher : String,
        @Query("subject") subject : String,
        @Query("chapter") chapter : Int
    ) : ResponsePronounceSentenceData
}