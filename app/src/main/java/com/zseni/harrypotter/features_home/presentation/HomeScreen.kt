package com.zseni.harrypotter.features_home.presentation


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.zseni.harrypotter.core.domain.model.BottomSheetEvent
import com.zseni.harrypotter.core.domain.model.CharactersItem
import com.zseni.harrypotter.core.domain.model.HogwartsEvent
import com.zseni.harrypotter.core.presentation.components.ImageShimmer
import com.zseni.harrypotter.core.presentation.components.shimmerEffect
import com.zseni.harrypotter.core.presentation.hogwartsViewModel.HogwartsVM
import com.zseni.harrypotter.features_categories.util.CategoryConstants
import com.zseni.harrypotter.features_home.domain.ResponseObserver
import com.zseni.harrypotter.features_home.presentation.components.HogwartsStaffSegment
import com.zseni.harrypotter.features_home.presentation.components.HogwartsStudentSection
import com.zseni.harrypotter.features_home.presentation.components.HomeTopAppBar
import com.zseni.harrypotter.features_home.presentation.components.WizardSection
import com.zseni.harrypotter.features_home.util.ConstHome
import com.zseni.harrypotter.navigation.screen.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    mainNavHostController: NavHostController,
    navHostController: NavHostController,
    hogwartsVM:HogwartsVM,
    state:ModalBottomSheetState,
    scope:CoroutineScope
){
    val lifeCycleOwner = LocalLifecycleOwner.current
    val direction = com.zseni.harrypotter.navigation.Direction(mainNavHostController)

    val allCharacter = remember {
        mutableStateOf<List<CharactersItem>>(emptyList())
    }

    val isErrorVisible = remember {
        mutableStateOf(false)
    }

    val responseObserver = remember {
        ResponseObserver{response->

            if (response.isSuccessful) {

                //Show main UI
                isErrorVisible.value = false

                response.body()?.let {
                    allCharacter.value = it
                }
            }else{
                isErrorVisible.value = true
            }
            Log.d("Interceptor",response.message())
        }
    }

    hogwartsVM.onEvent(HogwartsEvent.GetHogwartsCharacter)

    DisposableEffect(lifeCycleOwner, responseObserver ){
        hogwartsVM.harryPotterCharacters.observe(lifeCycleOwner,responseObserver)

        onDispose {
            hogwartsVM.harryPotterCharacters.removeObserver(responseObserver)
        }
    }

    var showAppIntro by remember {
        mutableStateOf(false)
    }

    var isSearching by remember {
        mutableStateOf(false)
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true){
        delay(10000)
        isLoading = false
    }

    AnimatedVisibility(
        visible = isSearching ) {

        SearchScreen(
            allCharacters = allCharacter.value ,
            onClearClicked = { isSearching = false },
            onCharacterClicked = {
                hogwartsVM.onBottomSheetEvent(
                    BottomSheetEvent.OpenBottomSheet(
                        state = state,
                        scope = scope,
                        bottomSheetType = ConstHome.DETAILS_BOTTOM_SHEET,
                        bottomSheetData = it
                    )
                )
            }
        )

    }

    AnimatedVisibility(visible = !isSearching) {
        //Show Main Concert

        IntroShowCaseScaffold(
            showIntroShowCase = showAppIntro,
            onShowCaseCompleted = { showAppIntro = true}
        ) {
            Scaffold(
                topBar = {
                    HomeTopAppBar(
                        onSearch = { isSearching = true },
                    onMore = {}
                    )
            }
            ){contentPadding->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding)
                ) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        if (allCharacter.value.isEmpty()
                        ){
                            // Show Shimmer Effects
                            ImageShimmer(
                                isLoading = isLoading,
                                contentAfterLoading = {
                                    LazyRow(
                                        content = {
                                            items(25){
                                                Column(modifier = Modifier
                                                    .fillMaxWidth()
                                                    .wrapContentHeight()
                                                    .shimmerEffect()
                                                    .background(MaterialTheme.colorScheme.onPrimary)
                                                    .padding(10.dp),
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                    verticalArrangement = Arrangement.Center
                                                ){
                                                    Box(
                                                        modifier = Modifier
                                                            .clip(RoundedCornerShape(16.dp))
                                                            .background(MaterialTheme.colorScheme.secondary)
                                                            .size(80.dp)
                                                    )

                                                    Spacer(modifier = Modifier.height(8.dp))

                                                    Box(modifier = Modifier
                                                        .clip(RoundedCornerShape(16.dp))
                                                        .background(MaterialTheme.colorScheme.secondary)
                                                        .width(120.dp)
                                                        .height(20.dp))

                                                    Spacer(modifier = Modifier.height(16.dp))

                                                    Box(
                                                        modifier = Modifier
                                                            .clip(RoundedCornerShape(16.dp))
                                                            .background(MaterialTheme.colorScheme.secondary)
                                                            .width(100.dp)
                                                            .height(20.dp))
                                                }
                                            }

                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(250.dp),
                                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                                        state = rememberLazyListState()
                                    )
                                }
                            )
                        }else{
                            // Wizards section
                             WizardSection(
                                 allWizards = allCharacter.value.filter { it.wizard},
                                 modifier = Modifier
                                     .wrapContentHeight(),
                                 onWizardClicked = {
                                    hogwartsVM.onBottomSheetEvent(
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

                            HogwartsStaffSegment(
                                allHogwartsStaff = allCharacter.value.filter { it.hogwartsStaff},
                                onCharacterClicked = {
                                    hogwartsVM.onBottomSheetEvent(
                                        BottomSheetEvent.OpenBottomSheet(
                                            state = state,
                                            scope = scope,
                                            bottomSheetType = ConstHome.DETAILS_BOTTOM_SHEET,
                                            bottomSheetData = it
                                        )
                                    )
                                },
                                onHouseClicked = {},
                                onSeeAll = {
                                    direction.navigateToRoute(
                                        Screens.Category.passCategory(CategoryConstants.Category_Staff),
                                        null

                                    )

                                }
                            )

                            HogwartsStudentSection(
                                allHogwartsStudents = allCharacter.value.filter { it.hogwartsStudent},
                                onCharacterClicked = {
                                     hogwartsVM.onBottomSheetEvent(
                                         BottomSheetEvent.OpenBottomSheet(
                                             state = state,
                                             scope = scope,
                                             bottomSheetData = it,
                                             bottomSheetType = ConstHome.DETAILS_BOTTOM_SHEET
                                         )
                                     )
                                },
                                onHouseClicked ={},
                                onSeeAll = {
                                    direction.navigateToRoute(
                                        Screens.Category.passCategory(CategoryConstants.Category_Student),
                                        null
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}