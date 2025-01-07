package com.aminuolawale.muffassa.domain.repository

import com.aminuolawale.muffassa.domain.model.Corpus
import kotlinx.coroutines.flow.Flow

interface CorpusRepository {
    fun getCorpora(userId: String): Flow<List<Corpus>>

    suspend fun getCorpus(id: Int): Corpus?

    suspend fun insertCorpus(corpus: Corpus): Long

    suspend fun deleteCorpus(id: Int)

    suspend fun deleteCorpora(ids: List<Int>)

}