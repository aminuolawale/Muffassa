package com.aminuolawale.muffassa.domain.repository

import com.aminuolawale.muffassa.domain.model.Corpus
import kotlinx.coroutines.flow.Flow

interface CorpusRepository {
    fun getCorpora(userId: String): Flow<List<Corpus>>

    suspend fun getCorpus(id: String): Corpus?

    suspend fun insertCorpus(corpus: Corpus): Long

    suspend fun deleteCorpus(id: String)

    suspend fun deleteCorpora(ids: List<String>)

}