package com.althaaf.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.althaaf.weatherapp.screens.about.AboutScreen
import com.althaaf.weatherapp.screens.favorite.FavoriteScreen
import com.althaaf.weatherapp.screens.home.HomeScreen
import com.althaaf.weatherapp.screens.home.HomeViewModel
import com.althaaf.weatherapp.screens.search.SearchScreen
import com.althaaf.weatherapp.screens.setting.SettingScreen
import com.althaaf.weatherapp.screens.setting.SettingViewmodel
import com.althaaf.weatherapp.screens.splash.SplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {
        composable(WeatherScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        val route = WeatherScreens.HomeScreen.name
        composable("$route/{city}", arguments = listOf(navArgument("city") {
            NavType.StringType
        })){ navBackStack ->
            navBackStack.arguments?.getString("city").let { city ->
                val homeViewModel: HomeViewModel = hiltViewModel()
                val settingViewModel: SettingViewmodel = hiltViewModel()
                HomeScreen(navController = navController, homeViewModel, settingViewModel, city)
            }
        }

        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        composable(WeatherScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }

        composable(WeatherScreens.SettingScreen.name) {
            SettingScreen(navController = navController)
        }

        composable(WeatherScreens.FavoriteScreen.name) {
            FavoriteScreen(navController = navController)
        }
    }
}