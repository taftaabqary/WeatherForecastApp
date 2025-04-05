package com.althaaf.weatherapp.screens.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.althaaf.weatherapp.model.Favorite
import com.althaaf.weatherapp.navigation.WeatherScreens
import com.althaaf.weatherapp.widgets.WeatherAppBar

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val favorites = favoriteViewModel.favorite.collectAsState().value

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            WeatherAppBar(
                title = "Favorite Cities",
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
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier,
                contentPadding = PaddingValues(bottom = 8.dp, start = 12.dp, end = 12.dp)
            ) {
                items(favorites, key = { it.city }) {
                    FavoriteCityRow(it, navController, favoriteViewModel)
                }
            }
        }
    }
}

@Composable
fun FavoriteCityRow(
    item: Favorite,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel
) {
    Surface(
        modifier = Modifier.fillMaxWidth()
            .clickable {
                navController.navigate(WeatherScreens.HomeScreen.name + "/${item.city}")
            },
        color = Color(0xFFB2DFDB),
        shape = RoundedCornerShape(
            topStart = 18.dp,
            topEnd = 0.dp,
            bottomEnd = 18.dp,
            bottomStart = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(item.city, style = MaterialTheme.typography.titleLarge)
            Surface(
                shape = CircleShape,
                color = Color(0xFFD1E3E1)
            ) {
                Text(item.country, style = MaterialTheme.typography.titleMedium)
            }
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Icon",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        favoriteViewModel.deleteFavCity(
                            favorite = Favorite(
                                country = item.country,
                                city = item.city
                            )
                        )
                    })
        }
    }
}
