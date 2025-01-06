package com.aminuolawale.muffassa.data.repository

import com.aminuolawale.muffassa.data.datasource.CorpusDao
import com.aminuolawale.muffassa.domain.model.Corpus
import com.aminuolawale.muffassa.domain.repository.CorpusRepository
import kotlinx.coroutines.flow.Flow

class CorpusRepositoryImpl(private val corpusDao: CorpusDao):CorpusRepository {
    override fun getCorpora(userId: String): Flow<List<Corpus>> {
        return corpusDao.getCorpora(userId)
    }

    override suspend fun getCorpus(id: Int): Corpus? {
        return corpusDao.getCorpus(id)
    }

    override suspend fun insertCorpus(corpus: Corpus): Long {
        return corpusDao.insertCorpus(corpus)
    }

    override suspend fun deleteCorpus(id: Int) {
        return corpusDao.deleteCorpus(id)
    }

}