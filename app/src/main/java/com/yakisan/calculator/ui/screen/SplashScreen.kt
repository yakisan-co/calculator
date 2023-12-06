package com.yakisan.calculator.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.yakisan.calculator.R
import com.yakisan.calculator.core.getTheme
import com.yakisan.calculator.navigation.Screen
import com.yakisan.calculator.ui.theme.CalculatorTheme
import com.yakisan.calculator.ui.theme.dimens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    //Splash to Home
    LaunchedEffect(true) {
        delay(1000) // 1 saniye gecikme -> HomeScreen
        navController.navigate(Screen.HomeScreen.route)
    }
    //Theme Control
    val theme = getTheme()
    val logo = if (isSystemInDarkTheme()) R.drawable.calculator_light_blue_logo else R.drawable.calculator_dark_blue_logo

    CalculatorTheme(
        statusBarColor = theme,
        navigationBarColor = theme
    ) {
        //Content
        Box(
            modifier = Modifier
                .background(color = theme)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(MaterialTheme.dimens.logoSize / 1.5f),
                painter = painterResource(id = logo),
                contentDescription = "Calculator logo"
            )
        }
    }
}