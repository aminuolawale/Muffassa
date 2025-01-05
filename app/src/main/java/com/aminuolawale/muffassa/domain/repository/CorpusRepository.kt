package com.aminuolawale.muffassa.domain.repository

import com.aminuolawale.muffassa.domain.model.Corpus
import kotlinx.coroutines.flow.Flow

interface CorpusRepository {
    fun getCorpora(): Flow<List<Corpus>>

    suspend fun getCorpus(id: Int): Corpus?

    suspend fun insertCorpus(corpus: Corpus): Long

    suspend fun deleteCorpus(id: Int)

}