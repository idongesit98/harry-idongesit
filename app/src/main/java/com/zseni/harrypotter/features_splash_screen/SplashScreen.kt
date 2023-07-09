package com.zseni.harrypotter.features_splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zseni.harrypotter.core.presentation.components.Lottie
import com.zseni.harrypotter.navigation.Direction
import com.zseni.harrypotter.navigation.NavConstants
import com.zseni.hogwarts_school.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    mainNavHostController: NavHostController
){
    val direction = Direction(mainNavHostController)

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.onPrimary)
        .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
    ) {
        Lottie(
            rawFile = R.raw.hufflepuff,
            isPlaying = true,
            iterations = Int.MAX_VALUE,
            modifier = Modifier
                .size(300.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))

        //Text
        Text(
            text = stringResource(id = R.string.splash_text),
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .wrapContentSize()
        )
    }

    LaunchedEffect(true){
        delay(3000)

        //Navigate to HomeScreen
        direction.navigateToRoute(
            NavConstants.Main_Route,
            NavConstants.Splash_Route
        )
    }

}