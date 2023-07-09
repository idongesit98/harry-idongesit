package com.zseni.harrypotter.features_home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.House
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.zseni.harrypotter.core.domain.model.CharactersItem
import com.zseni.harrypotter.core.presentation.components.ApiImage
import com.zseni.harrypotter.core.presentation.components.CharacterImage
import com.zseni.harrypotter.core.presentation.components.PillBtn
import com.zseni.hogwarts_school.R

/**
 * Handles the search items when they pop up how the images are organized
 * and displayed
 */
@Composable
fun SearchForCharacters(
    character:CharactersItem,
    onCharacterClicked:() -> Unit,
    onHouseClicked:()-> Unit
){
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .wrapContentSize()
            .background(MaterialTheme.colorScheme.onPrimary),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(16.dp)
                .clickable { onCharacterClicked() },
            verticalAlignment = Alignment.CenterVertically
        ){
         if (character.wizard){
// Displays image when a character is searched for
             CharacterImage(
                 context = context,
                 uri = character.image.toUri(),
                 imageSize = 60.dp,
                 boltSize = 16.dp,
             )
         }
            else{
                ApiImage(
                    context = context,
                    imageUri = character.image.toUri(),
                    imgHolder = R.drawable.profile_img,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(60.dp)
                )
         }
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                //Displays the character name when searched for
                Text(
                    text = character.name,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
                )

                PillBtn(
                    title = character.house,
                    startIcon = Icons.Filled.House,
                    horizontalPadding = 8.dp,
                    verticalPadding = 8.dp,
                    onClick  =onHouseClicked)
            }
        }
    }
}