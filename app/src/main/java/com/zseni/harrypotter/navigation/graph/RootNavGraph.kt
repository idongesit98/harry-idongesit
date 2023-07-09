package com.zseni.harrypotter.navigation.graph

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zseni.harrypotter.core.presentation.hogwartsViewModel.HogwartsVM
import com.zseni.harrypotter.navigation.NavConstants
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    hogwartsVM: HogwartsVM,
    modalSheetState: ModalBottomSheetState,
    scope:CoroutineScope
){
    NavHost(
        navController = navHostController,
        startDestination = NavConstants.Splash_Route
    ){
        mainNavGraph(
            navHostController = navHostController,
            hogwartsVm = hogwartsVM ,
            modalSheetState = modalSheetState,
            scope = scope
        )

        splashNavGraph(
            navHostController = navHostController
        )
    }
}