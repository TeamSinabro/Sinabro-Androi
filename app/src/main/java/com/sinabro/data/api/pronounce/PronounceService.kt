package com.sinabro.data.api.pronounce

import com.sinabro.data.model.request.RequestPronounceData
import com.sinabro.data.model.response.ResponsePronounceData
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PronounceService {

    @POST("/WiseASR/Pronunciation")
    suspend fun postPronounce(
        @Body requestPronounceData: RequestPronounceData
    ) : ResponsePronounceData
}