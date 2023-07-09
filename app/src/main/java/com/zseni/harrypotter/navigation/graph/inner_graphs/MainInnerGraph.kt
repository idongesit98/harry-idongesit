package com.zseni.harrypotter.navigation.graph.inner_graphs

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zseni.harrypotter.core.presentation.hogwartsViewModel.HogwartsVM
import com.zseni.harrypotter.features_home.presentation.HomeScreen
import com.zseni.harrypotter.features_houses.HouseScreen
import com.zseni.harrypotter.navigation.screen.BottomNavScreens
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainInnerGraph(
    mainNavHostController: NavHostController,
    navHostController: NavHostController,
    hogwartsVM: HogwartsVM,
    modalSheetState: ModalBottomSheetState,
    scope:CoroutineScope
){
    NavHost(
        navController = navHostController,
        startDestination = BottomNavScreens.Home.route
    ){
      composable(
          route = BottomNavScreens.Home.route
      ){
          HomeScreen(
              mainNavHostController,
              navHostController,
              hogwartsVM = hogwartsVM,
              state = modalSheetState,
              scope = scope
          )
      }
        composable(route = BottomNavScreens.Houses.route){
            HouseScreen(
                navHostController,
                hogwartsVM = hogwartsVM,
                state = modalSheetState,
                scope = scope
            )
        }

    }

}