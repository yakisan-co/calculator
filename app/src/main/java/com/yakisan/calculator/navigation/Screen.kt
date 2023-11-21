package com.yakisan.calculator.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen") //Splash
    object HomeScreen : Screen("home_screen") //Home
    //Other Screens
}