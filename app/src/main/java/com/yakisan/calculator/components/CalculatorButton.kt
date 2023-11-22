package com.yakisan.calculator.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.yakisan.calculator.extension.Extension.noRippleClickable
import com.yakisan.calculator.ui.theme.DarkColor
import com.yakisan.calculator.ui.theme.White

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalComposeUiApi
@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier = Modifier,
    textColor: Color = if(isSystemInDarkTheme()) White else DarkColor,
    textStyle: androidx.compose.ui.text.TextStyle = androidx.compose.ui.text.TextStyle(),
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(if(isSystemInDarkTheme()) DarkColor else White)
            .noRippleClickable {
                onClick()
            }
            .then(modifier)
    ) {
        Text(
            text = symbol,
            style = textStyle,
            color = textColor
        )
    }
}