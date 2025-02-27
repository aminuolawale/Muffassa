package com.aminuolawale.muffassa.domain.repository

import com.aminuolawale.muffassa.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface ResourceRepository {
    fun getResources(corpusId: String): Flow<List<Resource>>

    suspend fun getResource(id: String): Resource?

    suspend fun insertResource(resource: Resource)

    suspend fun deleteResource(id: String)

    suspend fun deleteResources(ids: List<String>)

}