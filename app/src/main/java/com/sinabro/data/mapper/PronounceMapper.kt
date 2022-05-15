package com.sinabro.data.mapper

import com.sinabro.data.model.request.RequestPronounceData
import com.sinabro.data.model.response.ResponsePronounceData
import com.sinabro.domain.model.request.PronouncePostItem
import com.sinabro.domain.model.response.PronouncePostData

object PronounceMapper {

    fun mapperToPronounceItem(pronouncePostItem: PronouncePostItem): RequestPronounceData {
        return RequestPronounceData(
            access_key = pronouncePostItem.access_key,
            argument = RequestPronounceData.Argument(
                language_code = pronouncePostItem.language_code,
                script = pronouncePostItem.script,
                audio = pronouncePostItem.audio

            )
        )
    }

    fun mapperToPronounceData(responsePronouncePostData: ResponsePronounceData): PronouncePostData {
        return PronouncePostData(
            score = responsePronouncePostData.return_object.score,
        )
    }
}