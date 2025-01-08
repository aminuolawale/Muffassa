package com.aminuolawale.muffassa.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aminuolawale.muffassa.domain.model.Resource
import kotlinx.coroutines.flow.Flow

@Dao
interface ResourceDao {
    @Query("SELECT * FROM resource where corpusId = :corpusId")
    fun getResources(corpusId: String): Flow<Resource>

    @Query("SELECT * FROM resource WHERE id = :id")
    suspend fun getResource(id:String): Resource?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResource(resource: Resource)

    @Query("DELETE FROM resource WHERE id=:id")
    suspend fun deleteResource(id: String)

    @Query("DELETE FROM resource WHERE id in (:ids)")
    suspend fun deleteResources(ids: List<String>)
}