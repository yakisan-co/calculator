package com.yakisan.calculator.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.yakisan.calculator.R
import com.yakisan.calculator.components.AlertDialog
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
    val showDialog = remember { mutableStateOf(false) } //Alert Dialog

    CalculatorTheme(navigationBarColor = getTheme()) {
        //Content
        LazyColumn {
            item {
                AppBar(
                    title = stringResource(R.string.history), icon = R.drawable.ic_delete,
                    onClick = {
                        showDialog.value = true

                    },
                )
            }
            items(histories.value.size) { history ->
                HistoryCard(
                    dayOfMonth = histories.value[history].dayOfMonth!!,
                    month = histories.value[history].month!!,
                    year = histories.value[history].year!!,
                    time = histories.value[history].time!!,
                    value = histories.value[history].value ?: "",
                    result = histories.value[history].result ?: "",
                )
            }
        }
    }

    if(showDialog.value){
        AlertDialog(
            //Email Not Found
            title = "Calculation Deletion",
            description = "All calculation history will be deleted, do you agree?",
            icon = R.drawable.ic_delete,
            buttonTitle = "Yes",
            //It should be active.
            buttonClicked = {
                showDialog.value = false
                viewModel.deleteHistories(histories = histories.value)
                println("Clicked delete & deleted histories")
            },
            showDialog = showDialog.value ,
            onDismiss = { showDialog.value  = false }
        )
    }



}