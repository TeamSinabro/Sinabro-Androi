package com.sinabro.domain.repository.vocasearch

import com.sinabro.domain.model.response.vocasearch.VocaSearchData

interface VocaSearchRepository {

    suspend fun getVocaSearch(keyword : String) : VocaSearchData
}