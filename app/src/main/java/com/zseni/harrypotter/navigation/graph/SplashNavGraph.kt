package com.zseni.harrypotter.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.zseni.harrypotter.features_splash_screen.SplashScreen
import com.zseni.harrypotter.navigation.NavConstants
import com.zseni.harrypotter.navigation.screen.Screens

fun NavGraphBuilder.splashNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = Screens.Splash.route,
        route = NavConstants.Splash_Route
    ){
        composable(route = Screens.Splash.route){
            SplashScreen(
                navHostController
            )
        }
    }

}