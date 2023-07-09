package com.zseni.harrypotter.navigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.House
import androidx.compose.ui.graphics.vector.ImageVector
import com.zseni.harrypotter.navigation.NavConstants

sealed class BottomNavScreens(
    val route:String,
    val icon: ImageVector,
    val title:String
) {

    object Home:BottomNavScreens(
        route = NavConstants.Home_Screen_Route,
        icon = Icons.Outlined.Home,
        title = "Home"
    )

    object Houses:BottomNavScreens(
        route = NavConstants.Houses_Screen_Route,
        icon = Icons.Outlined.House,
        title = "Houses"
    )
}