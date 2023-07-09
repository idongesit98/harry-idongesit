package com.zseni.harrypotter.core.domain.model
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import kotlinx.coroutines.CoroutineScope

sealed class BottomSheetEvent<T>{

    data class OpenBottomSheet<T> @OptIn(ExperimentalMaterialApi::class) constructor(
        val state:ModalBottomSheetState,
        val scope:CoroutineScope,
        val bottomSheetType:String,
        val bottomSheetData:T?
        ):BottomSheetEvent<T>()

    data class CloseBottomSheet @OptIn(ExperimentalMaterialApi::class) constructor(
        val state: ModalBottomSheetState,
        val scope:CoroutineScope
    ):BottomSheetEvent<Nothing>()
}


