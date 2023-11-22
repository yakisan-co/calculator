package com.yakisan.calculator.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen") //Splash
    object HomeScreen : Screen("home_screen") //Home
    object HistoryScreen : Screen("history_screen") //History

    //Other Screens
}