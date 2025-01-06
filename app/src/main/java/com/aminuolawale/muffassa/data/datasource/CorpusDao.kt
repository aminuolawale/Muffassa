package com.aminuolawale.muffassa.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aminuolawale.muffassa.domain.model.Corpus
import kotlinx.coroutines.flow.Flow


@Dao
interface CorpusDao {

    @Query("SELECT * FROM corpus where creator_user_id = :userId")
    fun getCorpora(userId: String): Flow<List<Corpus>>

    @Query("SELECT * FROM corpus WHERE id = :id")
    suspend fun getCorpus(id: Int): Corpus?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCorpus(corpus: Corpus): Long

    @Query("DELETE FROM corpus WHERE id=:id")
    suspend fun deleteCorpus(id: Int)
}