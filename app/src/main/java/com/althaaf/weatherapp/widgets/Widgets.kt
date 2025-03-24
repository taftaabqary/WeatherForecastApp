package com.althaaf.weatherapp.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.althaaf.weatherapp.R
import com.althaaf.weatherapp.model.WeatherItem
import com.althaaf.weatherapp.utils.ItemConverter.convertDecimalTemp
import com.althaaf.weatherapp.utils.ItemConverter.convertTimeToString
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun WeekRow(data: WeatherItem) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp, start = 8.dp, end = 8.dp),
        shape = RoundedCornerShape(
            topStart = 32.dp,
            topEnd = 0.dp,
            bottomStart = 32.dp,
            bottomEnd = 32.dp
        ),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                SimpleDateFormat("EEE", Locale.getDefault()).format(Date(data.dt.toLong() * 1000)),
                style = MaterialTheme.typography.titleMedium
            )
            AsyncImage(
                model = "https://openweathermap.org/img/wn/${data.weather[0].icon}.png",
                contentDescription = "Weather Icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(60.dp)
            )
            Surface(
                shape = RoundedCornerShape(50),
                color = Color(0xFFFFC400)
            ) {
                Text(data.weather[0].description, modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
            }
            Row(
                modifier = Modifier.padding(vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    convertDecimalTemp(data.temp.max) + "°",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                )

                Text(
                    convertDecimalTemp(data.temp.min) + "°",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray
                    )
                )
            }
        }
    }
}

@Composable
fun SunsetSunriseRow(data: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.sunrise),
                contentDescription = "Sunrise Icon",
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 4.dp)
            )
            Text(convertTimeToString(data.sunrise))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.sunset),
                contentDescription = "Sunset Icon",
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 4.dp)
            )
            Text(convertTimeToString(data.sunset))
        }
    }
}

@Composable
fun HumidityWindPressureRow(data: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.humidity),
                contentDescription = "Humidity Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(" ${data.humidity}%")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.pressure),
                contentDescription = "Pressure Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(" ${data.pressure} psi")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.wind),
                contentDescription = "Wind Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(" ${data.gust} mph")
        }
    }
}
