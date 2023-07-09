package com.zseni.harrypotter.features_home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zseni.harrypotter.core.domain.model.CharactersItem
import com.zseni.harrypotter.core.domain.model.HogwartsEvent
import com.zseni.harrypotter.core.presentation.components.Lottie
import com.zseni.harrypotter.core.presentation.hogwartsViewModel.HogwartsVM
import com.zseni.hogwarts_school.R

@Composable
fun SearchScreen(
    allCharacters:List<CharactersItem>,
    onClearClicked: () -> Unit,
    onCharacterClicked:(character:CharactersItem) -> Unit,
    hogwartsVM:HogwartsVM = hiltViewModel()
){
    val context = LocalContext.current
    hogwartsVM.onEvent(HogwartsEvent.GetHogwartsCharacter)

    val filteredCharacters = remember{
        mutableStateOf<List<CharactersItem>>(emptyList())
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.onPrimary)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            /**
             * Code below is to filter the information to look for the
             * provided query item
             */
            SearchTopBar(
                onQueryInput = { query ->
                    hogwartsVM.onEvent(HogwartsEvent.SearchCharacters(
                        query = query,
                        allCharacters = allCharacters,
                        filteredCharacter = {
                            filteredCharacters.value = it
                        }
                    ))
                }
            )
            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = {
                onClearClicked()
            }) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = stringResource(id = R.string.clear_btn),
                    tint = MaterialTheme.colors.error
                )
            }
        }

        // When searched character is not found
        AnimatedVisibility(
            visible = filteredCharacters.value.isEmpty()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Lottie(
                    rawFile = R.raw.searching,
                    isPlaying = true,
                    iterations = Int.MAX_VALUE,
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(id = R.string.search_response),
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.secondary
                )

            }
        }
// When characters are found
        AnimatedVisibility(
            visible = filteredCharacters.value.isNotEmpty()
        ) {

            val listState = rememberLazyListState()

            LazyColumn(
                content = {
                    items(filteredCharacters.value) { character ->
                        SearchForCharacters(
                            character = character,
                            onCharacterClicked = { onCharacterClicked(character) },
                            onHouseClicked = {}
                        )
                    }

                },
                state = listState,
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
        }
    }
}
