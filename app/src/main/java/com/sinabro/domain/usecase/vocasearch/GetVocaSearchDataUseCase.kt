package com.sinabro.domain.usecase.vocasearch

import com.sinabro.domain.model.response.vocasearch.VocaSearchData
import com.sinabro.domain.repository.vocasearch.VocaSearchRepository
import javax.inject.Inject

class GetVocaSearchDataUseCase @Inject constructor(
    private val repository : VocaSearchRepository
) {

    suspend operator fun invoke(keyword: String) : VocaSearchData{
        return repository.getVocaSearch(keyword)
    }
}