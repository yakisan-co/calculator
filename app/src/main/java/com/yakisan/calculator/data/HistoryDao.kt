package com.yakisan.calculator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {

    @Insert
    fun insertHistory(historyEntry: HistoryEntity)

    @Query("SELECT * FROM history ORDER BY timestamp DESC")
    fun getAllHistory(): List<HistoryEntity>
}