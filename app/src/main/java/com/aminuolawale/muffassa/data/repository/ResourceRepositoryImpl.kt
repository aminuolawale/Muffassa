package com.aminuolawale.muffassa.data.repository

import com.aminuolawale.muffassa.data.datasource.ResourceDao
import com.aminuolawale.muffassa.domain.model.Resource
import com.aminuolawale.muffassa.domain.repository.ResourceRepository
import kotlinx.coroutines.flow.Flow

class ResourceRepositoryImpl(private val resourceDao: ResourceDao): ResourceRepository {
    override fun getResources(corpusId: String): Flow<List<Resource>> {
        return resourceDao.getResources(corpusId)
    }

    override suspend fun getResource(id: String): Resource? {
        return resourceDao.getResource(id)
    }

    override suspend fun insertResource(resource: Resource) {
       return resourceDao.insertResource(resource)
    }

    override suspend fun deleteResource(id: String) {
        return resourceDao.deleteResource(id)
    }

    override suspend fun deleteResources(ids: List<String>) {
        return resourceDao.deleteResources(ids)
    }
}