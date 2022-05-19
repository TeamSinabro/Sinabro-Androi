package com.sinabro.domain.model.response.vocasearch

data class VocaSearchData(
    val keywordSource: List<String>,
    val sentence: List<String>,
    val vocaDefinition: List<String>
)
