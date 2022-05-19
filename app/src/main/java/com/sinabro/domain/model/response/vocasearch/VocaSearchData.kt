package com.sinabro.domain.model.response.vocasearch

data class VocaSearchData(
        val keywordSource: String,
        val sentence: List<String>,
        val vocaDefinition: List<String>
)
