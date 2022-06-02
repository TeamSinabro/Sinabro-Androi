package com.sinabro.data.api.qalearning

import com.sinabro.data.model.response.qalearning.ResponseQAData
import retrofit2.http.GET
import retrofit2.http.Query

interface QAService {

    @GET("/qaService")
    suspend fun getQAData(
        @Query("question") question : String
    ) : ResponseQAData
}