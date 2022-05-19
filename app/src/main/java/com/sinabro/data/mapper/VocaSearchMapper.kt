package com.sinabro.data.mapper

import com.sinabro.data.model.response.vocasearch.ResponseVocaSearchData
import com.sinabro.domain.model.response.vocasearch.VocaSearchData

object VocaSearchMapper {

    fun mapperToVocaSearchData(responseVocaSearchData: ResponseVocaSearchData) : VocaSearchData{
        return VocaSearchData(
            keywordSource= responseVocaSearchData.data.keywordSource,
        sentence = responseVocaSearchData.data.sentence,
        vocaDefinition = responseVocaSearchData.data.vocaDefinition
        )


    }
}