package com.zseni.harrypotter.features_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.House
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
 * This is for top wizards their names and their houses
 */
@Composable
fun CharacterItem(
    character:CharactersItem,
    onCharacterClicked:()-> Unit,
    onHouseClicked: () -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .wrapContentSize()
            .background(MaterialTheme.colorScheme.onPrimary),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .clickable { onCharacterClicked() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ){
            if (character.wizard){
                CharacterImage(
                    context = context,
                    uri = character.image.toUri(),
                    imageSize = 80.dp,
                    boltSize = 25.dp )
            }else{
                ApiImage(
                    context = context ,
                    imageUri = character.image.toUri(),
                    imgHolder = R.drawable.profile_img,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(80.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = character.name,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            PillBtn(
                title = character.house,
                startIcon = Icons.Outlined.House,
                horizontalPadding = 8.dp,
                verticalPadding = 8.dp,
                onClick = onHouseClicked
            )
        }


    }
}