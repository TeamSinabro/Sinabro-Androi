package com.sinabro.data.model.response

data class ResponsePronounceData(
    val request_id : String,
    val result : Double,
    val return_object : ReturnObject
){
    data class ReturnObject(
        val recognized : String,
        val score : Double
    )

}
