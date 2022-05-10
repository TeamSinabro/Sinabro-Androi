package com.sinabro.data.repositoryimpl.pronounce

import com.sinabro.data.datasource.remote.pronounce.PronounceDataSource
import com.sinabro.data.mapper.PronounceMapper
import com.sinabro.domain.model.request.PronouncePostItem
import com.sinabro.domain.model.response.PronouncePostData
import com.sinabro.domain.repository.pronounce.PronounceRepository
import javax.inject.Inject

class PronounceRepositoryImpl @Inject constructor(
    private val pronounceDataSource: PronounceDataSource
) : PronounceRepository {
    override suspend fun postPronounce(pronouncePostItem: PronouncePostItem): PronouncePostData {
        return PronounceMapper.mapperToPronounceData(
            pronounceDataSource.postPronounce(PronounceMapper.mapperToPronounceItem(
                pronouncePostItem
            ))
        )
    }
}