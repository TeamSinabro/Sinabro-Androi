package com.sinabro.domain.model.request

data class PronouncePostItem(
    val access_key: String,
    val language_code: String,
    val script: String,
    val audio: String
)
