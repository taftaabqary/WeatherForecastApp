package com.althaaf.weatherapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.althaaf.weatherapp.model.WeatherResponse
import com.althaaf.weatherapp.utils.ApiResult
import com.althaaf.weatherapp.utils.ItemConverter.convertDecimalTemp
import com.althaaf.weatherapp.utils.ItemConverter.convertTimeZoneToDate
import com.althaaf.weatherapp.widgets.HumidityWindPressureRow
import com.althaaf.weatherapp.widgets.SunsetSunriseRow
import com.althaaf.weatherapp.widgets.WeatherAppBar
import com.althaaf.weatherapp.widgets.WeekRow

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {

    val weather = produceState(
        initialValue =
        ApiResult<WeatherResponse, Boolean, Exception>(loading = true)
    ) {
        value = homeViewModel.getWeatherForecasting()
    }

    if (weather.value.loading == true) {
        CircularProgressIndicator()
    } else if (weather.value.data != null) {
        MainScaffold(weather.value.data!!, navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(weather: WeatherResponse, navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            WeatherAppBar(
                title = weather.city.name + ", ${weather.city.country}",
                elevation = 6.dp
            )
        }
    ) { innerPadding ->
        MainContent(modifier = Modifier.padding(innerPadding), weather)
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier, weather: WeatherResponse) {

    val thisDay = weather.list[0]

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            convertTimeZoneToDate(thisDay.dt),
            modifier = Modifier.padding(vertical = 12.dp),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Surface(
            modifier = Modifier
                .padding(8.dp)
                .size(220.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = "https://openweathermap.org/img/wn/${thisDay.weather[0].icon}.png",
                    contentDescription = "Weather Icon",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(80.dp)
                )
                Text(
                    convertDecimalTemp(thisDay.temp.day) + "°",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    thisDay.weather[0].main, style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic
                    )
                )
            }
        }
        HumidityWindPressureRow(data = thisDay)
        HorizontalDivider()
        SunsetSunriseRow(data = thisDay)
        Text(
            "This Week", style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Surface(
            modifier = Modifier.padding(top = 2.dp),
            shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp),
            color = Color.LightGray.copy(alpha = 0.3f)
        ) {
            LazyColumn(
                modifier = Modifier,
                contentPadding = PaddingValues(top = 12.dp)
            ) {
                items(weather.list) {
                    WeekRow(it)
                }
            }
        }
    }
}