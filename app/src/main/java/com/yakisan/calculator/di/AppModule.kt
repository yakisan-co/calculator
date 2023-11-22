package com.yakisan.calculator.di

import android.content.Context
import androidx.room.Room
import com.yakisan.calculator.data.HistoryDao
import com.yakisan.calculator.data.HistoryDatabase
import com.yakisan.calculator.viewmodel.CalculatorViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideHistoryDatabase(@ApplicationContext context: Context): HistoryDatabase {
        return Room.databaseBuilder(context, HistoryDatabase::class.java, "history.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideHistoryDao(database: HistoryDatabase): HistoryDao {
        return database.historyDao()
    }
}