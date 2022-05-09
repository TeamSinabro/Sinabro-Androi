package com.sinabro.data.model.request

data class RequestPronounceData(
    val access_key : String,
    val argument : Argument,
){
    data class Argument(
        val language_code : String,
        val script : String,
        val audio : String
    )
}
