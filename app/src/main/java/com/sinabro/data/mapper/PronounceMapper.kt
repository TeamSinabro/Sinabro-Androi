package com.sinabro.data.mapper

import com.sinabro.data.model.request.RequestPronounceData
import com.sinabro.data.model.response.pronounce.ResponsePronounceData
import com.sinabro.data.model.response.pronounce.ResponsePronounceSentenceData
import com.sinabro.domain.model.request.PronouncePostItem
import com.sinabro.domain.model.response.pronounce.PronounceGetSentenceData
import com.sinabro.domain.model.response.pronounce.PronouncePostData

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
            recognized = responsePronouncePostData.return_object.recognized
        )
    }

    //발음 평가 문제 가져오기
    fun mapperToPronounceSentenceData(responsePronounceSentenceData : ResponsePronounceSentenceData) : PronounceGetSentenceData {
        return PronounceGetSentenceData(
            problem = responsePronounceSentenceData.data.problem
        )
    }
}