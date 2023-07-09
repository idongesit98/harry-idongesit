package com.zseni.harrypotter.features_houses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zseni.harrypotter.core.domain.model.CharactersItem
import com.zseni.harrypotter.core.domain.model.HogwartsEvent
import com.zseni.harrypotter.core.presentation.hogwartsViewModel.HogwartsVM
import com.zseni.harrypotter.features_home.domain.ResponseObserver
import com.zseni.harrypotter.features_houses.components.HouseAppBar
import com.zseni.harrypotter.features_houses.components.HouseList
import com.zseni.harrypotter.navigation.Direction
import com.zseni.harrypotter.navigation.screen.BottomNavScreens
import com.zseni.hogwarts_school.R
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HouseScreen(
    navHostController: NavHostController,
    hogwartsVM: HogwartsVM,
    state:ModalBottomSheetState,
    scope:CoroutineScope
){
    val directionInner = Direction(navHostController)
    val lifeCycleOwner = LocalLifecycleOwner.current

    val allCharacters = remember{
        mutableStateOf<List<CharactersItem>>(emptyList())
    }
    val responseObserver = remember {
        ResponseObserver{response ->
            response.body()?.let {
                allCharacters.value = it
            }
        }
    }

    //Get all characters
    hogwartsVM.onEvent(HogwartsEvent.GetHogwartsCharacter)

    DisposableEffect(lifeCycleOwner, hogwartsVM){
        hogwartsVM.harryPotterCharacters.observe(lifeCycleOwner,responseObserver)

        onDispose {
            hogwartsVM.harryPotterCharacters.removeObserver(responseObserver)
        }
    }

    val allHouses = allCharacters.value.map { it.house }.distinct()

    Scaffold(
        topBar ={
            HouseAppBar(
                title = stringResource(id = R.string.houses),
                onBackPressed = {
                    //Navigate back to home Screen
                    directionInner.navigateToRoute(
                        BottomNavScreens.Home.route,
                        BottomNavScreens.Home.route
                    )
                }
            )
        }
    ) {contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp)
            ) {
                //Display a list of all Houses
                HouseList(
                    allHouses = allHouses,
                    hogwartsVm = hogwartsVM,
                    state = state,
                    scope = scope
                )
            }
        }
    }
}