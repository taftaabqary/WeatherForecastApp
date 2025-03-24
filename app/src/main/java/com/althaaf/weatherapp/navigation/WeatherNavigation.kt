package com.althaaf.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.althaaf.weatherapp.screens.home.HomeScreen
import com.althaaf.weatherapp.screens.home.HomeViewModel
import com.althaaf.weatherapp.screens.splash.SplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {
        composable(WeatherScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(WeatherScreens.HomeScreen.name) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(navController = navController, homeViewModel)
        }
    }
}