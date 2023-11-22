package com.yakisan.calculator.core

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import com.yakisan.calculator.ui.theme.DarkBlue
import com.yakisan.calculator.ui.theme.LightBlue

object Constant {
    const val MAX_NUM_LENGTH = 18
}

//get size info
object Size {
    //get current screen full height
    @Composable
    fun height(): Int {
        val configuration = LocalConfiguration.current
        return configuration.screenHeightDp
    }

    //get current screen full width
    @Composable
    fun width(): Int {
        val configuration = LocalConfiguration.current
        return configuration.screenWidthDp
    }
}


@Composable
fun getTheme(): Color {
    return if (isSystemInDarkTheme()) DarkBlue else LightBlue
}

@Composable
fun getTextTheme() : Color {
    return if(isSystemInDarkTheme()) LightBlue else DarkBlue
}