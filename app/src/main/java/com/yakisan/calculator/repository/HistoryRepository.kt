package com.yakisan.calculator.repository

import androidx.lifecycle.LiveData
import com.yakisan.calculator.data.History
import com.yakisan.calculator.data.HistoryDao

class HistoryRepository(private val historyDao: HistoryDao) {

    val allHistories: LiveData<List<History>> = historyDao.getAllHistories()

    suspend fun insertHistory(history: History) {
        historyDao.insertHistory(history)
    }

    suspend fun deleteHistories(histories: List<History>) {
        historyDao.deleteHistory(histories)
    }
}
