package com.zseni.harrypotter.features_home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.School
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zseni.harrypotter.core.domain.model.CharactersItem
import com.zseni.hogwarts_school.R

@Composable
fun HogwartsStaffSegment(
    allHogwartsStaff:List<CharactersItem>,
    onCharacterClicked: (character:CharactersItem) -> Unit,
    onHouseClicked:(house:String) -> Unit,
    onSeeAll: () -> Unit
){
    val listState = rememberLazyListState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
    verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        SectionRef(
            title = stringResource(id = R.string.hogwarts_staff),
            icon = Icons.Outlined.School,
            onSeeAll = onSeeAll
        )
        LazyRow(
            content = {
                items(allHogwartsStaff){
                    AltCharacter(
                        character = it,
                        onCharacterClicked = { onCharacterClicked(it)},
                        onHouseClicked = {onHouseClicked(it.house)}
                    )
                }
            },
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
    }

}