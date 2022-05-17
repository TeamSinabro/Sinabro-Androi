package com.sinabro.data.model.response.vocasearch

data class ResponseVocaSearchData(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val keywordSource: String,
        val sentence: List<String>,
        val vocaDefinition: List<String>
    )
}