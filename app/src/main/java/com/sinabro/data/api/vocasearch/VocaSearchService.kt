package com.sinabro.data.api.vocasearch

import com.sinabro.data.model.response.vocasearch.ResponseVocaSearchData
import retrofit2.http.GET
import retrofit2.http.Query

interface VocaSearchService {

    @GET("/voca/search")
    suspend fun getVocaSearch(
        @Query("keyword") keyword : String
    ) : ResponseVocaSearchData
}