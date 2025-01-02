package com.aminuolawale.muffassa.di


import android.app.Application
import androidx.room.Room
import com.aminuolawale.muffassa.data.datasource.MuffassaDatabase
import com.aminuolawale.muffassa.data.repository.CorpusRepositoryImpl
import com.aminuolawale.muffassa.domain.repository.CorpusRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesMuffassaDatabase(application: Application):MuffassaDatabase = Room.databaseBuilder(
        application,
        MuffassaDatabase::class.java,
        MuffassaDatabase.DATABASE_NAME
    ).build()

    fun providesCorpusRepository(muffassaDatabase: MuffassaDatabase):CorpusRepository =
        CorpusRepositoryImpl(muffassaDatabase.corpusDao)


}