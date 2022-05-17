package com.sinabro.domain.repository.pronounce

import com.sinabro.domain.model.request.PronouncePostItem
import com.sinabro.domain.model.response.pronounce.PronouncePostData

interface PronounceRepository {

    suspend fun postPronounce(pronouncePostItem: PronouncePostItem) : PronouncePostData
}