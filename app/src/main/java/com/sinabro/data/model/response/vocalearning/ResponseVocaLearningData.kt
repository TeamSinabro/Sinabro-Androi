package com.sinabro.data.model.response.vocalearning

data class ResponseVocaLearningData(
    val `data`: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val answer: String,
        val answerSource: String?,
        val optionList: List<String>,
        val problem: String,
        val vocaDefinition: List<String>
    )
}