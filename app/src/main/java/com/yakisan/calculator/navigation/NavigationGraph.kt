package com.yakisan.calculator.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yakisan.calculator.ui.screen.HistoryScreen
import com.yakisan.calculator.ui.screen.HomeScreen
import com.yakisan.calculator.ui.screen.SplashScreen
import com.yakisan.calculator.viewmodel.CalculatorViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController() //NavController Value
    //Host
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    )
    {
        //Splash Screen
        composable(route = Screen.SplashScreen.route)
        {
            SplashScreen(navController)
        }

        //Home Screen
        composable(route = Screen.HomeScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            })
        {
            HomeScreen(navController)
        }

        //Home Screen
        composable(route = Screen.HistoryScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            })
        {
            HistoryScreen(navController = navController)
        }

    }
}