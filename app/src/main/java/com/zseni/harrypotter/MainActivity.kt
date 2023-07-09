package com.zseni.harrypotter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.zseni.harrypotter.core.domain.model.BottomSheetEvent
import com.zseni.harrypotter.core.domain.model.CharactersItem
import com.zseni.harrypotter.core.presentation.components.BottomSheet
import com.zseni.harrypotter.core.presentation.hogwartsViewModel.HogwartsVM
import com.zseni.harrypotter.features_home.presentation.components.DetailBottomSheet
import com.zseni.harrypotter.features_home.util.ConstHome
import com.zseni.harrypotter.navigation.graph.RootNavGraph
import com.zseni.harrypotter.ui.theme.HarryPotterTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var application:HogwartsApp
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HarryPotterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   val hogwartsVM:HogwartsVM = hiltViewModel()

                    BottomSheet(
                        sheetBackground = MaterialTheme.colorScheme.onPrimary,
                        topStartRadius = 0.dp,
                        topEndRadius = 0.dp,
                        sheetContent = { state, scope ->
                            when (hogwartsVM.bottomSheetContent.value){
                                ConstHome.DETAILS_BOTTOM_SHEET ->{
                                    DetailBottomSheet(
                                        character = hogwartsVM.bottomSheetData.value as CharactersItem,
                                        onBackPressed = {
                                            //Close Bottom Sheet
                                            hogwartsVM.onBottomSheetEvent(
                                                BottomSheetEvent.CloseBottomSheet(
                                                    state = state,
                                                    scope = scope
                                                )
                                            )
                                        }
                                    )
                                }
                            }
                        },
                        sheetScope = {
                                     state, scope ->
                            RootNavGraph(
                                navHostController = rememberNavController(),
                                hogwartsVM = hogwartsVM ,
                                modalSheetState = state ,
                                scope = scope
                            )
                        },
                        closeBottomSheet = { state, scope ->
                        hogwartsVM.onBottomSheetEvent(BottomSheetEvent.CloseBottomSheet(state,scope))
                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HarryPotterTheme {
    }
}