package com.aminuolawale.muffassa.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "corpus")
data class Corpus(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    @ColumnInfo(name= "creator_user_id") val creatorUserId: String,
    @ColumnInfo(name= "date_created")  val dateCreated: Long = System.currentTimeMillis(),
    @ColumnInfo(name= "creator_user_id") val lastUpdated: Long = System.currentTimeMillis()
)