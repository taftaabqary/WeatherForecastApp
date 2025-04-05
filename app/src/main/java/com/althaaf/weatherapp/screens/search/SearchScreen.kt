package com.althaaf.weatherapp.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.althaaf.weatherapp.navigation.WeatherScreens
import com.althaaf.weatherapp.widgets.WeatherAppBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController
    ) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            WeatherAppBar(
                title = "Search",
                isMainScreen = false,
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                onButtonClicked = {
                    navController.popBackStack()
                },
                navController = navController
            )
        }
    ) { paddingValues ->
        Surface(modifier = modifier) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(paddingValues)
            ) {
                WeatherSearchBar(
                    modifier = Modifier.fillMaxWidth().padding(16.dp).align(Alignment.CenterHorizontally),
                    onSearch = { city ->
                        navController.navigate(WeatherScreens.HomeScreen.name + "/$city")
                    }
                )
            }
        }
    }
}

@Composable
fun WeatherSearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    val searchCity = rememberSaveable {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    val valid = remember(searchCity.value){
        searchCity.value.trim().isNotEmpty()
    }

    Column(
        modifier = modifier
    ) {
        SearchCityInput(
            searchValue = searchCity,
            title = "Enter City",
            keyboardActions = KeyboardActions {
                if(!valid) return@KeyboardActions
                onSearch(searchCity.value)
                keyboardController?.hide()
            }
        )
    }
}

@Composable
fun SearchCityInput(
    searchValue: MutableState<String>,
    title: String,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = searchValue.value,
        onValueChange = {
            searchValue.value = it
        },
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        label = { Text(title) },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon") },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType,
        ),
        keyboardActions = keyboardActions
    )
}
