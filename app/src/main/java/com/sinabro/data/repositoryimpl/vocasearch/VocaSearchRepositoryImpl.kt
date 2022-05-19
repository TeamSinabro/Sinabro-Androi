package com.sinabro.data.repositoryimpl.vocasearch

import com.sinabro.data.datasource.remote.vocasearch.VocaSearchDataSource
import com.sinabro.data.mapper.VocaSearchMapper
import com.sinabro.domain.model.response.vocasearch.VocaSearchData
import com.sinabro.domain.repository.vocasearch.VocaSearchRepository
import javax.inject.Inject

class VocaSearchRepositoryImpl @Inject constructor(
    private val dataSource : VocaSearchDataSource
) : VocaSearchRepository {

    override suspend fun getVocaSearch(keyword: String): VocaSearchData {
        return VocaSearchMapper.mapperToVocaSearchData(dataSource.getVocaSearch(keyword))
    }
}