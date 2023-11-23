package com.yakisan.calculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakisan.calculator.data.History
import com.yakisan.calculator.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val historyRepository: HistoryRepository) : ViewModel() {

    val historyList: LiveData<List<History>> = historyRepository.allHistories
    val foundHistory = MutableLiveData<History>()

    fun insertHistory(history: History) {
        viewModelScope.launch {
            historyRepository.insertHistory(history)
        }
    }

    fun deleteHistories(histories: List<History>) {
        viewModelScope.launch {
            historyRepository.deleteHistories(histories = histories)
        }
    }
}