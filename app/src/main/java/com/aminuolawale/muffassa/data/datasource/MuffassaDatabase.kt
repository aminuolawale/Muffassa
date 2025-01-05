package com.aminuolawale.muffassa.data.datasource


import androidx.room.Database
import androidx.room.RoomDatabase
import com.aminuolawale.muffassa.domain.model.Corpus


@Database(entities = [Corpus::class], version = 1)
abstract class MuffassaDatabase: RoomDatabase() {
    abstract val corpusDao: CorpusDao
    companion object {
        const val  DATABASE_NAME = "muffassa_3"
    }
}