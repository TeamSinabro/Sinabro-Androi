package com.sinabro.data.datasource.remote.vocasearch

import com.sinabro.data.model.response.vocasearch.ResponseVocaSearchData

interface VocaSearchDataSource {

    suspend fun getVocaSearch(keyword : String) : ResponseVocaSearchData
}