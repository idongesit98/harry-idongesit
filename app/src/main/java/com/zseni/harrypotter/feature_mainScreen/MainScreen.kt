package com.zseni.harrypotter.feature_mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.zseni.harrypotter.core.presentation.hogwartsViewModel.HogwartsVM
import com.zseni.harrypotter.navigation.graph.inner_graphs.MainInnerGraph
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navHostController: NavHostController,
    hogwartsVm:HogwartsVM,
    state:ModalBottomSheetState,
    scope:CoroutineScope
) {
    val navController = rememberNavController()

    var showAppIntro by remember {
        mutableStateOf(false)
    }

    IntroShowCaseScaffold(
        showIntroShowCase = showAppIntro,
        onShowCaseCompleted = { showAppIntro = false}
    ) {
        Scaffold(
            bottomBar = {
                MainBottomNav(navHostController = navController)
            }
        ) {contentPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(contentPadding)
            ) {
                MainInnerGraph(
                    mainNavHostController = navHostController,
                    navHostController = navController,
                    hogwartsVM = hogwartsVm,
                    modalSheetState = state,
                    scope = scope
                )

            }

        }


    }
}