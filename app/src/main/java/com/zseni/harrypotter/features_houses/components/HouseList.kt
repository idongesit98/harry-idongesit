package com.zseni.harrypotter.features_houses.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import com.zseni.harrypotter.core.domain.model.BottomSheetEvent
import com.zseni.harrypotter.core.domain.model.CharactersItem
import com.zseni.harrypotter.core.domain.model.HogwartsEvent
import com.zseni.harrypotter.core.presentation.hogwartsViewModel.HogwartsVM
import com.zseni.harrypotter.features_home.domain.ResponseObserver
import com.zseni.harrypotter.features_home.util.ConstHome
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HouseList(
    allHouses:List<String>,
    hogwartsVm:HogwartsVM,
    state:ModalBottomSheetState,
    scope:CoroutineScope
){
    val listState = rememberLazyListState()
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

    DisposableEffect(lifeCycleOwner, hogwartsVm){
        hogwartsVm.harryPotterCharacters.observe(lifeCycleOwner,responseObserver)

            onDispose {

                hogwartsVm.harryPotterCharacters.removeObserver(responseObserver)
            }
    }

    hogwartsVm.onEvent(HogwartsEvent.GetHogwartsCharacter)

    //populate a list of house
    LazyColumn(
        content = {
            items(allHouses){ house ->
                val charactersInHouse = allCharacters.value.filter { it.house == house }

                HouseListItem(
                    houseName = house,
                    characterInHouse = charactersInHouse,
                    onCharacterClicked = {
                          hogwartsVm.onBottomSheetEvent(
                              BottomSheetEvent.OpenBottomSheet(
                                  state = state,
                                  scope = scope,
                                  bottomSheetType = ConstHome.DETAILS_BOTTOM_SHEET,
                                  bottomSheetData = it
                              )
                          )
                    },
                    characterHouse = {}
                )
            }
        },
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    )
}