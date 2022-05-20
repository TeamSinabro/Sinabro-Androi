package com.sinabro.data.api.vocalearning

import com.sinabro.data.model.response.vocalearning.ResponseVocaLearningData
import retrofit2.http.GET
import retrofit2.http.Query

interface VocaLearningService {

    @GET("/voca/problem")
    suspend fun getVocaLearning(
        @Query("publisher") publisher : String,
        @Query("subject") subject : String,
        @Query("chapter") chapter : Int
    ) : ResponseVocaLearningData
}