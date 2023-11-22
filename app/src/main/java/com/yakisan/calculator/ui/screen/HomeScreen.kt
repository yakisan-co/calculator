package com.yakisan.calculator.ui.screen

import android.app.Activity
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.yakisan.calculator.components.AppBar
import com.yakisan.calculator.components.CalculatorButton
import com.yakisan.calculator.core.getTextTheme
import com.yakisan.calculator.core.getTheme
import com.yakisan.calculator.navigation.Screen
import com.yakisan.calculator.ui.theme.CalculatorTheme
import com.yakisan.calculator.ui.theme.DarkColor
import com.yakisan.calculator.ui.theme.DarkGray
import com.yakisan.calculator.ui.theme.White
import com.yakisan.calculator.ui.theme.Yellow
import com.yakisan.calculator.ui.theme.dimens
import com.yakisan.calculator.domain.CalculatorAction
import com.yakisan.calculator.domain.CalculatorOperation
import com.yakisan.calculator.viewmodel.CalculatorViewModel

@OptIn(ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current as Activity?

    CalculatorTheme(
        navigationBarColor = if (isSystemInDarkTheme()) DarkColor else White
    ) {
        val viewModel = viewModel<CalculatorViewModel>() //VM
        val state = viewModel.state
        val result = state.number1 + (state.operation?.symbol ?: "") + state.number2
        val buttonSpacing = 8.dp

        val resultFontStatus = remember { mutableStateOf(50.sp) }
        if (result.length > 8) resultFontStatus.value = 30.sp else resultFontStatus.value = 50.sp


        //Content
        Column(
            modifier = Modifier
                .background(color = getTheme())
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                // Navigating history screen
                AppBar(
                    historyOnClick = {
                        navController.navigate(Screen.HistoryScreen.route)
                    },
                )

                //ALL PROCESS TEXT
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(
                            end = MaterialTheme.dimens.medium1,
                            top = MaterialTheme.dimens.large / 2
                        ),
                    text = result,
                    style = MaterialTheme.typography.headlineLarge.copy(color = DarkGray),
                    textAlign = TextAlign.End,
                    maxLines = 1
                )

                //SUMMARY TEXT
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(
                            end = MaterialTheme.dimens.medium1,
                        ),
                    text = result,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = getTextTheme(),
                        fontSize = resultFontStatus.value
                    ),
                    textAlign = TextAlign.End,
                    maxLines = 1
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = MaterialTheme.dimens.large / 2f)
                    .background(color = if (isSystemInDarkTheme()) DarkColor else White),
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    verticalArrangement = Arrangement.spacedBy(buttonSpacing),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                    ) {
                        CalculatorButton(
                            symbol = "AC",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(900),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )

                        ) {
                            viewModel.onAction(CalculatorAction.Clear)
                        }
                        CalculatorButton(
                            symbol = "Del",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(500),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        ) {
                            viewModel.onAction(CalculatorAction.Delete)
                        }

                        //TODO: Yuzde islemi yapmalı
                        CalculatorButton(
                            symbol = "%",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(500),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        ) {
                            viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Percent))
                        }
                        CalculatorButton(
                            symbol = "/",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textColor = Yellow,
                            textStyle = TextStyle(fontWeight = FontWeight(500), fontSize = 35.sp)
                        ) {
                            viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                    ) {
                        CalculatorButton(
                            symbol = "7",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(500),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        ) {
                            viewModel.onAction(CalculatorAction.Number(7))
                        }
                        CalculatorButton(
                            symbol = "8",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(500),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )

                        ) {
                            viewModel.onAction(CalculatorAction.Number(8))
                        }
                        CalculatorButton(
                            symbol = "9",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(500),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        ) {
                            viewModel.onAction(CalculatorAction.Number(9))
                        }
                        CalculatorButton(
                            symbol = "x",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textColor = Yellow,
                            textStyle = TextStyle(fontWeight = FontWeight(500), fontSize = 35.sp)
                        ) {
                            viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                    ) {
                        CalculatorButton(
                            symbol = "4",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(500),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        ) {
                            viewModel.onAction(CalculatorAction.Number(4))
                        }
                        CalculatorButton(
                            symbol = "5",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(500),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        ) {
                            viewModel.onAction(CalculatorAction.Number(5))
                        }
                        CalculatorButton(
                            symbol = "6",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(500),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        ) {
                            viewModel.onAction(CalculatorAction.Number(6))
                        }
                        CalculatorButton(
                            symbol = "-",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textColor = Yellow,
                            textStyle = TextStyle(fontWeight = FontWeight(500), fontSize = 35.sp)
                        ) {
                            viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                    ) {
                        CalculatorButton(
                            symbol = "1",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(500),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        ) {
                            viewModel.onAction(CalculatorAction.Number(1))
                        }
                        CalculatorButton(
                            symbol = "2",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(500),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        ) {
                            viewModel.onAction(CalculatorAction.Number(2))
                        }
                        CalculatorButton(
                            symbol = "3",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(500),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        ) {
                            viewModel.onAction(CalculatorAction.Number(3))
                        }
                        CalculatorButton(
                            symbol = "+",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textColor = Yellow,
                            textStyle = TextStyle(fontWeight = FontWeight(500), fontSize = 35.sp)
                        ) {
                            viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                    ) {
                        //TODO: +/- YAPILMALI
                        CalculatorButton(
                            symbol = "00",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(500),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        ) {
                            viewModel.onAction(CalculatorAction.AddZero)
                        }
                        CalculatorButton(
                            symbol = "0",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(500),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        ) {
                            viewModel.onAction(CalculatorAction.Number(0))
                        }
                        CalculatorButton(
                            symbol = ".",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textStyle = TextStyle(
                                fontWeight = FontWeight(500),
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize
                            )
                        ) {
                            viewModel.onAction(CalculatorAction.Decimal)
                        }
                        CalculatorButton(
                            symbol = "=",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f),
                            textColor = Yellow,
                            textStyle = TextStyle(fontWeight = FontWeight(500), fontSize = 35.sp)
                        ) {
                            viewModel.onAction(CalculatorAction.Calculate)
                        }
                    }
                }


            }
        }
    }

    //If you tap back button, finish activity.
    BackHandler {
        context?.finish()
    }
}