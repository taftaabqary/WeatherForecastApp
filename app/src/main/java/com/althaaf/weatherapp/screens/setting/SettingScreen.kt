package com.althaaf.weatherapp.screens.setting

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.althaaf.weatherapp.model.Unit
import com.althaaf.weatherapp.widgets.WeatherAppBar

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    settingViewmodel: SettingViewmodel = hiltViewModel()
) {

    val context = LocalContext.current
    var checkedState by remember {
        mutableStateOf(false)
    }

    val unitFromDb = settingViewmodel.units.collectAsState().value
    val defaultValue =
        if(unitFromDb.isEmpty()) "Celcius °C" else unitFromDb[0].unit

    val unitValue = remember(defaultValue) {
        mutableStateOf(defaultValue)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            WeatherAppBar(
                title = "Setting",
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
            Text("Change units of measurement", style = MaterialTheme.typography.titleLarge)

            IconToggleButton(
                modifier = Modifier.fillMaxWidth(fraction = 0.5f)
                    .padding(vertical = 12.dp)
                    .background(Color.Yellow),
                checked = checkedState,
                onCheckedChange = {
                    checkedState = !checkedState
                    unitValue.value = if(checkedState) "Celcius °C" else "Fahrenheit °F"
                }
            ) {
                Text(unitValue.value, style = MaterialTheme.typography.titleMedium)
            }

            Button(
                modifier = Modifier,
                onClick = {
                    settingViewmodel.deleteAllUnit()
                    settingViewmodel.insertNewUnit(Unit(unitValue.value)).run {
                        Toast.makeText(context, "Updated unit to $unitValue", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEFBE42), contentColor = Color.White),
                shape = RoundedCornerShape(18.dp)
            ) {
                Text("Save", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}