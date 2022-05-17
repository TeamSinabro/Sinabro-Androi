package com.sinabro.data.datasource.remote.pronounce

import com.sinabro.data.api.pronounce.PronounceService
import com.sinabro.data.model.request.RequestPronounceData
import com.sinabro.data.model.response.pronounce.ResponsePronounceData
import javax.inject.Inject

class PronounceDataSourceImpl @Inject constructor(
    private val service : PronounceService
) : PronounceDataSource {
    override suspend fun postPronounce(requestPronounceData: RequestPronounceData): ResponsePronounceData {
        return service.postPronounce(requestPronounceData)
    }
}