package com.yakisan.calculator.core

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.yakisan.calculator.ui.theme.DarkBlue
import com.yakisan.calculator.ui.theme.LightBlue

object Constant {
    val APPNAME = "Calculator"
}

@Composable
fun getTheme(): Color {
    return if (isSystemInDarkTheme()) DarkBlue else LightBlue
}