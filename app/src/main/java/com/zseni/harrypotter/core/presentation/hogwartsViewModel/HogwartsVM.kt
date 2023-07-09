package com.zseni.harrypotter.core.presentation.hogwartsViewModel


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zseni.harrypotter.core.domain.model.*
import com.zseni.harrypotter.core.domain.use_cases.HogwartsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HogwartsVM @Inject constructor(
    private val hogwartsUseCases: HogwartsUseCases
):ViewModel() {
    val harryPotterCharacters = MutableLiveData<Response<List<CharactersItem>>>()
    val hogwartsStaff = MutableLiveData<Response<List<CharactersItem>>>()
    val hogwartsStudents = MutableLiveData<Response<List<CharactersItem>>>()
    val hogwartsSpells = MutableLiveData<Response<List<Spell>>>()
    val characterInHouse = MutableLiveData<Response<List<CharactersItem>>>()
    val selectedCharacter = MutableLiveData<Response<CharactersItem>>()

    //BottomSheet
    private val _bottomSheetContent = mutableStateOf("")
    val bottomSheetContent: State<String> = _bottomSheetContent

    private val _bottomSheetData = mutableStateOf<Any?>(null)
    var bottomSheetData: State<Any?> = _bottomSheetData


    fun onEvent(event: HogwartsEvent) {
        when (event) {

            is HogwartsEvent.GetHogwartsCharacter -> {
                viewModelScope.launch {
                    harryPotterCharacters.value = hogwartsUseCases.hogwartsCharacter()
                }
            }

            is HogwartsEvent.HogwartsCharacterId -> {
                viewModelScope.launch {
                    selectedCharacter.value = hogwartsUseCases
                        .hogwartsCharacterIdUseCase(id = event.id)
                }
            }

            is HogwartsEvent.GetHogwartsStaff -> {
                viewModelScope.launch {
                    hogwartsStaff.value = hogwartsUseCases.hogwartsAllStaff()
                }
            }

            is HogwartsEvent.GetHogwartsSpells -> {
                viewModelScope.launch {
                    hogwartsSpells.value = hogwartsUseCases.hogwartsSpells()
                }
            }

            is HogwartsEvent.GetHogwartsStudents -> {
                viewModelScope.launch {
                    hogwartsStudents.value = hogwartsUseCases.hogwartsStudents()
                }
            }

            is HogwartsEvent.GetHogwartsCharactersByHouse -> {
                viewModelScope.launch {
                    characterInHouse.value =
                        hogwartsUseCases.hogwartsCharacterByHouse(houseName = event.houseName)
                }
            }

            is HogwartsEvent.SearchCharacters -> {
                viewModelScope.launch {
                    // search for characters in the list if characters
                    val allCharacters = event.allCharacters.toMutableList()
                    val filteredList = mutableListOf<CharactersItem>()

                    allCharacters.forEach { character ->
                        if (character.name.lowercase(Locale.ROOT).contains(event.query) ||
                            character.house.lowercase(Locale.ROOT).contains(event.query)
                        ) {

                            filteredList.add(character)
                        }
                    }
                    event.filteredCharacter(filteredList)
                }
            }

        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    fun onBottomSheetEvent(event: BottomSheetEvent<*>) {
//        val modalSheetState = rememberModalBottomSheetState(
//            initialValue = ModalBottomSheetValue.Hidden,
//            confirmValueChange = {it != ModalBottomSheetValue.HalfExpanded},
//            skipHalfExpanded = false
//        )

        //val coroutineScope = rememberCoroutineScope()

        //val isSheetFullScreen by remember{ mutableStateOf(false) }

        when (event) {
            is BottomSheetEvent.OpenBottomSheet<*> -> {

                event.scope.launch {
                    //sheetScaffoldState.show()
                    event.state.show()
                }

                viewModelScope.launch {
                    _bottomSheetContent.value = event.bottomSheetType
                    _bottomSheetData.value = event.bottomSheetData
                }
            }

            is BottomSheetEvent.CloseBottomSheet -> {
                event.scope.launch {
                    event.state.hide()
                }

            }
        }

    }
}