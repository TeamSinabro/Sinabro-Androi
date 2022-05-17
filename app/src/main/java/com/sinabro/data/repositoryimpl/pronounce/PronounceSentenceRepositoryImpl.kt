package com.sinabro.data.repositoryimpl.pronounce

import com.sinabro.data.datasource.remote.pronounce.PronounceSentenceDataSource
import com.sinabro.data.mapper.PronounceMapper
import com.sinabro.domain.model.response.pronounce.PronounceGetSentenceData
import com.sinabro.domain.repository.pronounce.PronounceSentenceRepository
import javax.inject.Inject

class PronounceSentenceRepositoryImpl @Inject constructor(
    val dataSource : PronounceSentenceDataSource
) : PronounceSentenceRepository {
    override suspend fun getPronunciationSentence(
        publisher: String,
        subject: String,
        chapter: Int
    ): PronounceGetSentenceData {
        return PronounceMapper.mapperToPronounceSentenceData(
            dataSource.getPronunciationSentence(publisher, subject, chapter)
        )
    }
}