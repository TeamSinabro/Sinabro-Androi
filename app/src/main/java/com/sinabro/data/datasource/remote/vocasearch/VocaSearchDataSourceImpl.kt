package com.sinabro.data.datasource.remote.vocasearch

import com.sinabro.data.api.vocasearch.VocaSearchService
import com.sinabro.data.model.response.vocasearch.ResponseVocaSearchData
import javax.inject.Inject

class VocaSearchDataSourceImpl @Inject constructor(
    private val service : VocaSearchService
) : VocaSearchDataSource {

    override suspend fun getVocaSearch(keyword: String): ResponseVocaSearchData {
        return service.getVocaSearch(keyword)
    }
}