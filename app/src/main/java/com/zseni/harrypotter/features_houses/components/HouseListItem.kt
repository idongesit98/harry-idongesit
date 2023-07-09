package com.zseni.harrypotter.features_houses.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zseni.harrypotter.core.domain.model.CharactersItem
import com.zseni.harrypotter.features_home.presentation.components.AltCharacter

@Composable
fun HouseListItem(
    houseName:String,
    characterInHouse: List<CharactersItem>,
    onCharacterClicked:(character:CharactersItem) -> Unit,
    characterHouse:(house:String)-> Unit
){
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(8.dp),
    verticalArrangement = Arrangement.spacedBy(24.dp)
    ){
        Text(
            text = houseName,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary
        )
        LazyRow(
            content = {
                items(characterInHouse){
                    AltCharacter(
                        character = it,
                        containerColor = MaterialTheme.colorScheme.onSecondary,
                        onCharacterClicked = {onCharacterClicked(it)},
                        onHouseClicked = {characterHouse(it.house)}
                    )
                }
            },

            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        )


    }

}