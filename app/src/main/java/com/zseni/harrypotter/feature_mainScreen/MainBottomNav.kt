package com.zseni.harrypotter.feature_mainScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zseni.harrypotter.navigation.screen.BottomNavScreens

@Composable
fun MainBottomNav(
    navHostController: NavHostController
){
    val screens = listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Houses
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination =
        screens.any { it.route == currentDestination?.route }

    val isBottomVisible = remember{
        mutableStateOf(true)
    }

    isBottomVisible.value = bottomBarDestination

    // show/hide bottom bar depending on the current screen

    AnimatedVisibility(visible = isBottomVisible.value) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 8.dp),
        contentAlignment = Alignment.Center
        ){
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .clip(RoundedCornerShape(32.dp))
                    .fillMaxWidth(0.8f)
                    .wrapContentHeight()
            ) {
               screens.forEach {
                   MainBottomNavItem(
                       navHostController = navHostController,
                       currentDestination = currentDestination,
                       screen = it
                   )
               }
            }
        }
    }
}