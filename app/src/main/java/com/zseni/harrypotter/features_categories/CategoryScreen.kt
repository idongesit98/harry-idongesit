package com.zseni.harrypotter.features_categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zseni.harrypotter.core.domain.model.BottomSheetEvent
import com.zseni.harrypotter.core.domain.model.CharactersItem
import com.zseni.harrypotter.core.domain.model.HogwartsEvent
import com.zseni.harrypotter.core.presentation.hogwartsViewModel.HogwartsVM
import com.zseni.harrypotter.features_categories.components.CategoryTopBar
import com.zseni.harrypotter.features_categories.util.CategoryConstants
import com.zseni.harrypotter.features_home.domain.ResponseObserver
import com.zseni.harrypotter.features_home.presentation.components.AltCharacter
import com.zseni.harrypotter.features_home.util.ConstHome
import com.zseni.harrypotter.navigation.Direction
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryScreen(
    mainNavHostController: NavHostController,
    categoryContent:String,
    hogwartsVm:HogwartsVM,
    state:ModalBottomSheetState,
    scope:CoroutineScope
){
    val lifeCycleOwner = LocalLifecycleOwner.current
    val direction = Direction(mainNavHostController)
    val gridState = rememberLazyStaggeredGridState()

    val allCharacters = remember{
        mutableStateOf<List<CharactersItem>>(emptyList())
    }

    /**
     * val responseObserver: This line declares a variable named responseObserver of type ResponseObserver.
     * It is using the val keyword, which means it is an immutable (read-only) variable.

    remember { ... }: This is a function provided by Jetpack Compose. It allows you to create and remember a value across recompositions of the component.
    It ensures that the value is not recomputed unnecessarily and retains its state even when the UI is recomposed.

    ResponseObserver() { response -> ... }: This creates a new instance of the ResponseObserver class and passes a lambda function as an argument.
    The lambda function takes a response parameter.

    response.body()?.let { ... }: This line checks if the body() function of the response object is not null.
    If it's not null, the let function is called with the non-null value. The let function provides a way to execute code only if the value is not null and safely access it within the lambda function.

    allCharacters.value = it: This line assigns the value of it (the non-null response.body()) to the value property of allCharacters.
    It seems that allCharacters is a variable of some sort of mutable state (possibly a MutableState) that holds the value of all characters.

    Overall, this code sets up a ResponseObserver instance and uses it to handle responses by assigning the received characters to the
    allCharacters variable or state.
     */

    val responseObserver = remember{
        ResponseObserver(){response ->
            response.body()?.let {
                allCharacters.value = it
            }
        }
    }

    val icon = when(categoryContent){
        CategoryConstants.Category_Staff -> Icons.Outlined.School
        CategoryConstants.Category_Student -> Icons.Outlined.Person
        else -> Icons.Outlined.Person
    }

    if (categoryContent == CategoryConstants.Category_Student)
        hogwartsVm.onEvent(HogwartsEvent.GetHogwartsStaff)
    else if(categoryContent == CategoryConstants.Category_Staff)
        hogwartsVm.onEvent(HogwartsEvent.GetHogwartsStudents)

    DisposableEffect(lifeCycleOwner, hogwartsVm){
        when(categoryContent){
            CategoryConstants.Category_Staff -> {
                hogwartsVm.hogwartsStaff.observe(lifeCycleOwner,responseObserver)

                onDispose {
                    hogwartsVm.hogwartsStaff.removeObserver(responseObserver)
                }
            }

            CategoryConstants.Category_Student ->{
                hogwartsVm.hogwartsStudents.observe(lifeCycleOwner,responseObserver)

                onDispose {
                    hogwartsVm.hogwartsStudents.removeObserver(responseObserver)
                }
            }


            else ->{
                hogwartsVm.hogwartsStaff.observe(lifeCycleOwner,responseObserver)

                onDispose {
                    hogwartsVm.hogwartsStaff.removeObserver(responseObserver)
                }
            }
        }
    }
    Scaffold(
        topBar = {
            CategoryTopBar(
                title = categoryContent,
                icon = icon,
                onBackPressed = {
                    direction.navigateUp
                }
            )
        }
    ){ contentPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(contentPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                //List of characters
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    content = {
                        items(allCharacters.value){
                            AltCharacter(
                                character = it,
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
                                onHouseClicked = {}
                            )
                        }
                    },
                    state = gridState,
                    contentPadding = PaddingValues(16.dp),
                    verticalItemSpacing = 16.dp
                )
            }
        }
    }

}