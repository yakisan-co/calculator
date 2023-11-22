package com.yakisan.calculator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {

        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        fun getInstance(context: Context): HistoryDatabase {
            synchronized(this) {
                val instance = INSTANCE ?: Room.databaseBuilder(
                    context,
                    HistoryDatabase::class.java,
                    "history.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}