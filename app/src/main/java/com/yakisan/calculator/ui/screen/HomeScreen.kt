package com.yakisan.calculator.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.navigation.NavController
import com.yakisan.calculator.core.Constant
import com.yakisan.calculator.core.getTheme
import com.yakisan.calculator.ui.theme.DarkBlue
import com.yakisan.calculator.ui.theme.LightBlue

@Composable
fun HomeScreen(navController: NavController) {
    //Content
    Box(
        modifier = Modifier
            .background(color = getTheme())
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {}
}