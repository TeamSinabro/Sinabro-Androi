package com.sinabro.domain.model.response.vocalearning

data class VocaGetLearningData(
    val answer: String,
    val answerSource: String?,
    val optionList: List<String>,
    val problem: String,
    val vocaDefinition: List<String>
)
