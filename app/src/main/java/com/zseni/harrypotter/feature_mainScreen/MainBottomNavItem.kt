package com.zseni.harrypotter.feature_mainScreen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.zseni.harrypotter.navigation.screen.BottomNavScreens
import com.zseni.hogwarts_school.R

@Composable
fun RowScope.MainBottomNavItem(
    navHostController: NavHostController,
    currentDestination:NavDestination?,
    screen:BottomNavScreens
){
    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navHostController.navigate(route = screen.route){
                popUpTo(BottomNavScreens.Home.route)
                launchSingleTop = true
            }
        },
        alwaysShowLabel = true,
        
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = stringResource(id = R.string.bottom_nav)
            )
        },
        
        label = {
            Text(
                text = screen.title,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.SemiBold
            )
        },

        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = MaterialTheme.colorScheme.primary,
            selectedIconColor = MaterialTheme.colorScheme.secondary,
            indicatorColor = MaterialTheme.colorScheme.tertiary
        )
    )
}
