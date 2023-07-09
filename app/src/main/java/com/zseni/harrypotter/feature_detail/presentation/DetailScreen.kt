package com.zseni.harrypotter.feature_detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.canopas.lib.showcase.IntroShowCaseScaffold
import com.zseni.harrypotter.core.domain.model.CharactersItem
import com.zseni.harrypotter.core.presentation.hogwartsViewModel.HogwartsVM
import com.zseni.harrypotter.feature_detail.model.DetailObserver
import com.zseni.harrypotter.navigation.Direction

@Composable
fun DetailScreen(
    navHostController: NavHostController,
){
    val direction = Direction(navHostController)
    val lifeCycleOwner = LocalLifecycleOwner.current
    val hogwartsVM: HogwartsVM = hiltViewModel()

    var showAppIntro by remember{
        mutableStateOf(false)
    }

    val characterDetails = remember {
        mutableStateOf<CharactersItem?>(null)
    }

    val characterObserver = remember {
        DetailObserver{response->
            if (response.isSuccessful){
                response.body().let {
                    characterDetails.value = it
                }
            }
        }
    }

    DisposableEffect(lifeCycleOwner, hogwartsVM){
        hogwartsVM.selectedCharacter.observe(lifeCycleOwner,characterObserver)

        onDispose {
            hogwartsVM.selectedCharacter.removeObserver(characterObserver)
        }
    }

    IntroShowCaseScaffold(
        showIntroShowCase = showAppIntro,
        onShowCaseCompleted = { showAppIntro = false }
    ) {
        Scaffold(
            topBar ={
                DetailTopBar(
                    title = characterDetails.value?.name?: "" ) {
                    direction.navigateUp
                }
            }
        ) {contentPadding->
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
                }
            }
        }
    }
}
