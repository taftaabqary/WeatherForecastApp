package com.althaaf.weatherapp.widgets

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.althaaf.weatherapp.model.Favorite
import com.althaaf.weatherapp.navigation.WeatherScreens
import com.althaaf.weatherapp.screens.favorite.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    elevation: Dp = 0.dp,
    onActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    val splitData = title.split(",")
    val openPopUp = remember {
        mutableStateOf(false)
    }

    if (openPopUp.value) {
        PopUpMenu(
            navController = navController,
            popUpState = openPopUp
        )
    }

    val context = LocalContext.current

    CenterAlignedTopAppBar(
        modifier = modifier.shadow(elevation = elevation),
        title = {
            Text(
                title, style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        },
        actions = {
            if (isMainScreen) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Icon Search",
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .clickable {
                            onActionClicked.invoke()
                        }
                )

                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Icon More",
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .clickable {
                            openPopUp.value = true
                        }
                )
            } else {
                Box() {}
            }
        },
        navigationIcon = {
            if (icon != null)
                Icon(
                    imageVector = icon,
                    contentDescription = "Icon Navigation",
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    })

            if (isMainScreen) {
                val favorite = favoriteViewModel.favorite.collectAsState().value.filter {
                    it.city == splitData[0]
                }

                if(favorite.isEmpty()) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Icon Favorite",
                        tint = Color.Red.copy(alpha = 0.6F),
                        modifier = Modifier.clickable {
                            favoriteViewModel.insertFavCity(Favorite(
                                city = splitData[0],
                                country = splitData[1]
                            )).run {
                               Toast.makeText(context, "Save ${splitData[0]} to favorite", Toast.LENGTH_SHORT).show()
                            }
                        })
                } else {
                    Box{}
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}

@Composable
fun PopUpMenu(
    modifier: Modifier = Modifier,
    navController: NavController,
    popUpState: MutableState<Boolean>
) {

    val listMenu = listOf("Favorite", "About", "Setting")

    var expanded by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 60.dp, right = 16.dp),
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                popUpState.value = false
            },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            listMenu.forEachIndexed { index, menu ->
                DropdownMenuItem(
                    text = {
                        Text(menu)
                    },
                    onClick = {
                        val route = when (menu) {
                            "Favorite" -> WeatherScreens.FavoriteScreen.name
                            "About" -> WeatherScreens.AboutScreen.name
                            else -> WeatherScreens.SettingScreen.name
                        }
                        popUpState.value = false
                        navController.navigate(route)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = when (index) {
                                0 -> Icons.Filled.Favorite
                                1 -> Icons.Filled.Info
                                else -> Icons.Filled.Settings
                            },
                            contentDescription = "Icon Menu"
                        )
                    }
                )
            }
        }
    }
}
