package com.aminuolawale.muffassa.data.datasource


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aminuolawale.muffassa.domain.model.Corpus
import com.aminuolawale.muffassa.domain.model.Resource


@Database(entities = [Corpus::class, Resource::class], version = 1)
@TypeConverters(Converter::class)
abstract class MuffassaDatabase : RoomDatabase() {
    abstract val corpusDao: CorpusDao
    abstract val resourceDao: ResourceDao
    companion object {
        const val DATABASE_NAME = "muffassa_9"
    }
}