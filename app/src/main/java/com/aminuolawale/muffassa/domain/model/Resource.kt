package com.aminuolawale.muffassa.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resource")
data class Resource(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val corpusId: String,
    val data: ResourceData,
    @ColumnInfo(name = "date_created") val dateCreated: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "last_updated") val lastUpdated: Long = System.currentTimeMillis()
)


data class ResourceData(val type: ResourceType, val value: String)

enum class ResourceType {
    ARTICLE,
    FILE,
    NOTE,
}