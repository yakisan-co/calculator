package com.yakisan.calculator.di

import android.app.Application
import android.content.Context
import com.yakisan.calculator.data.HistoryDao
import com.yakisan.calculator.data.HistoryDatabase
import com.yakisan.calculator.repository.HistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideHistoryDatabase(@ApplicationContext context: Context): HistoryDatabase {
        return HistoryDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideHistoryDao(database: HistoryDatabase): HistoryDao {
        return database.historyDao()
    }

    @Provides
    @Singleton
    fun provideHistoryRepository(historyDao: HistoryDao): HistoryRepository {
        return HistoryRepository(historyDao)
    }
}
