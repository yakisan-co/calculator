package com.yakisan.calculator.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.yakisan.calculator.R
import com.yakisan.calculator.components.AlertDialog
import com.yakisan.calculator.components.AppBar
import com.yakisan.calculator.components.HistoryCard
import com.yakisan.calculator.core.getTheme
import com.yakisan.calculator.ui.theme.CalculatorTheme
import com.yakisan.calculator.ui.theme.DarkColor
import com.yakisan.calculator.ui.theme.dimens
import com.yakisan.calculator.viewmodel.HistoryViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val histories = viewModel.historyList.observeAsState(emptyList())
    val showDialog = remember { mutableStateOf(false) } //Alert Dialog
    val showNullList = remember { mutableStateOf(false) }

    CalculatorTheme(navigationBarColor = getTheme()) {
        //Content
        LazyColumn {
            item {
                AppBar(
                    title = stringResource(R.string.history), icon = R.drawable.ic_delete,
                    onClick = {
                        if (!showNullList.value) showDialog.value = true
                    },
                )
            }
            if (histories.value.isEmpty()) {
                showDialog.value = false
                showNullList.value = true
            } else {
                showNullList.value = false
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
    }

    if (showDialog.value) {
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
            showDialog = showDialog.value,
            onDismiss = { showDialog.value = false }
        )
    }

    if (showNullList.value) {
        Box(modifier = Modifier.fillMaxSize().padding(MaterialTheme.dimens.large), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.empty_list_image),
                contentDescription = "",
                colorFilter = ColorFilter.tint(if(isSystemInDarkTheme()) Color.White else DarkColor)
            )
        }
    }


}