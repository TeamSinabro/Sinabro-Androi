package com.sinabro.domain.model.response

data class PronouncePostData(
    val request_id : String,
    val result : Double,
    val return_object : String
)
