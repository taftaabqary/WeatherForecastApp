package com.althaaf.weatherapp.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.althaaf.weatherapp.R
import com.althaaf.weatherapp.widgets.WeatherAppBar

@Composable
fun AboutScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            WeatherAppBar(
                title = "About",
                isMainScreen = false,
                navController = navController,
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                onButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(LocalContext.current.getString(R.string.about_app), style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ))

            Text(LocalContext.current.getString(R.string.api_used), style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Light
            ))
        }
    }
}