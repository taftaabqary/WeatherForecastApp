package com.althaaf.weatherapp.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    modifier: Modifier = Modifier,
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    onActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
    ) {
    CenterAlignedTopAppBar(
        modifier = modifier.shadow(elevation = elevation),
        title = {
            Text(title, style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ))
        },
        actions = {
            if (isMainScreen) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Icon Search",
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .clickable {  }
                )

                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Icon More",
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .clickable {  }
                )
            } else {
                Box() {}
            }
        },
        navigationIcon = {
            if(icon != null)
                Icon(imageVector = icon, contentDescription = "Icon Navigation", modifier = Modifier.clickable {
                    onButtonClicked()
                })
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}