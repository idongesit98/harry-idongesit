package com.zseni.harrypotter.features_home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zseni.harrypotter.core.domain.model.CharactersItem
import com.zseni.hogwarts_school.R

@Composable
fun WizardSection(
    modifier: Modifier = Modifier,
    allWizards:List<CharactersItem>,
    onWizardClicked:(character:CharactersItem)->Unit,
    onHouseClicked:(house:String)->Unit
){
    val listState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier
            .wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Outlined.Bolt,
                contentDescription = stringResource(id = R.string.bolt),
                tint = MaterialTheme.colorScheme.surfaceTint
            )
            // Title

            Text(
                text = stringResource(id = R.string.top_wizards),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
            )
        }
        LazyRow(
            content ={
                items(allWizards){character ->
                    CharacterItem(
                        character = character ,
                        onHouseClicked = {onHouseClicked(character.house)},
                        onCharacterClicked = {
                            onWizardClicked(character)
                        }
                    )
                }
            } ,
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        )
    }

}