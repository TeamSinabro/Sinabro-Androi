package com.sinabro.data.model.response

data class ResponsePronounceSentenceData(
    val data: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val problem: String
    )
}