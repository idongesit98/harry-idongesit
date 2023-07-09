package com.zseni.harrypotter.core.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Minimize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zseni.hogwarts_school.R
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
     sheetBackground:Color,
     topStartRadius: Dp = 16.dp,
     topEndRadius:Dp = 16.dp,
     sheetContent: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
     sheetScope: @Composable (state: ModalBottomSheetState, scope:CoroutineScope) -> Unit,
     closeBottomSheet:(state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
){

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            Icon(
                imageVector = Icons.Outlined.Minimize,
                contentDescription = stringResource(id = R.string.dash_icon),
                tint = MaterialTheme.colorScheme.onSecondary.copy(alpha =0.5f),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            sheetContent(modalBottomSheetState,scope)
        },
        content = {
            sheetScope(modalBottomSheetState,scope)
        },
        sheetState = modalBottomSheetState,
        sheetElevation = 20.dp,
        sheetShape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = topEndRadius
        ),
        sheetBackgroundColor = sheetBackground
    )

    // Back button
    BackHandler(
        enabled = modalBottomSheetState.isVisible
    ){
      closeBottomSheet(modalBottomSheetState, scope)
    }

}
