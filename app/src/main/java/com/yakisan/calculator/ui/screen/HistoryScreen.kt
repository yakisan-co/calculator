package com.yakisan.calculator.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.yakisan.calculator.viewmodel.CalculatorViewModel

@Composable
fun HistoryScreen(
    viewModel: CalculatorViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val history = viewModel.getHistory()

    LazyColumn {
        items(history) { historyEntry ->
            historyEntry.result
        }
    }
}