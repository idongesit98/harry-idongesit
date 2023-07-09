package com.zseni.harrypotter.navigation.graph


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.zseni.harrypotter.core.presentation.hogwartsViewModel.HogwartsVM
import com.zseni.harrypotter.feature_detail.presentation.DetailScreen
import com.zseni.harrypotter.feature_mainScreen.MainScreen
import com.zseni.harrypotter.features_categories.CategoryScreen
import com.zseni.harrypotter.features_splash_screen.SplashScreen
import com.zseni.harrypotter.navigation.NavConstants
import com.zseni.harrypotter.navigation.screen.Screens
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.mainNavGraph(
    navHostController: NavHostController,
    hogwartsVm:HogwartsVM,
    modalSheetState:ModalBottomSheetState,
    scope: CoroutineScope
) {
    navigation(
        startDestination = Screens.Main.route,
        route = NavConstants.Main_Route
    ) {
        composable(route = Screens.Splash.route) {
            SplashScreen(
                navHostController
            )
        }

        composable(route = Screens.Main.route) {
            MainScreen(
                navHostController = navHostController,
                hogwartsVm = hogwartsVm,
                state = modalSheetState,
                scope = scope
            )

        }

        composable(
            route = Screens.Category.route,
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) {
            CategoryScreen(
                mainNavHostController = navHostController,
                categoryContent = it.arguments?.getString("category")?: "no category",
                hogwartsVm = hogwartsVm,
                state = modalSheetState,
                scope = scope
            )

        }

        composable(
            route = Screens.Detail.route,
            arguments = listOf(
                navArgument("model") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen(
                navHostController = navHostController,
            )
        }
    }
}