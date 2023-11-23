package com.yakisan.calculator.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.yakisan.calculator.R
import com.yakisan.calculator.components.AppBar
import com.yakisan.calculator.components.HistoryCard
import com.yakisan.calculator.core.getTheme
import com.yakisan.calculator.ui.theme.CalculatorTheme
import com.yakisan.calculator.viewmodel.HistoryViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val histories = viewModel.historyList.observeAsState(emptyList())

    CalculatorTheme(navigationBarColor = getTheme()) {
        //Content
        LazyColumn {
            item {
                AppBar(
                    title = stringResource(R.string.history), icon = R.drawable.ic_delete,
                    onClick = {
                        viewModel.deleteHistories(histories = histories.value)
                    },
                )
            }
            items(histories.value.size) { history ->
                HistoryCard(
                    dayOfMonth = histories.value[history].dayOfMonth!!,
                    month = histories.value[history].month!!,
                    year = histories.value[history].year!!,
                    value = histories.value[history].value ?: "",
                    result = histories.value[history].result ?: "",
                )
            }
        }
    }
}