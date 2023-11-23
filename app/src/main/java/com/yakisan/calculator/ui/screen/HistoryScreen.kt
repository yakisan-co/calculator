package com.yakisan.calculator.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.yakisan.calculator.components.AppBar
import com.yakisan.calculator.components.HistoryCard
import com.yakisan.calculator.domain.CalculatorOperation
import com.yakisan.calculator.model.History
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreen(
    navController: NavHostController,
) {
    val current = LocalDateTime.now()

    val history : List<History> = listOf(
        History(current, "522 - 2", "520"),
        History(current, "522 - 2", "520"),
        History(current, "522 - 2", "520"),
        History(current, "522 - 2", "520"),
    )
    Column {
        // Navigating history screen
        AppBar(
            title = "History",
        )

        LazyColumn {
            items(history.size) { item ->
                HistoryCard(date = history[item].date, value =history[item].value , result = history[item].result, operation = CalculatorOperation.Add)
            }
        }
    }





}