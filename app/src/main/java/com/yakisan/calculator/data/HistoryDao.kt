package com.yakisan.calculator.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history ORDER BY uid DESC")
    fun getAllHistories(): LiveData<List<History>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHistory(history: History)

    @Delete
    suspend fun deleteHistory(histories: List<History>)
}
