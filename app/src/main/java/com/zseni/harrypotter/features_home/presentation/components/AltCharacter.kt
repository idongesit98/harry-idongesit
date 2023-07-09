package com.zseni.harrypotter.features_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenWith
import androidx.compose.material.icons.outlined.House
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.zseni.harrypotter.core.domain.model.CharactersItem
import com.zseni.harrypotter.core.presentation.components.ApiImage
import com.zseni.harrypotter.core.presentation.components.CharacterImage
import com.zseni.harrypotter.core.presentation.components.PillBtn
import com.zseni.hogwarts_school.R

@Composable
fun AltCharacter(
    character:CharactersItem,
    onCharacterClicked: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.onPrimary,
    onHouseClicked: () -> Unit ,
){
    val context = LocalContext.current

    Card(modifier = Modifier
        .clip(RoundedCornerShape(16.dp))
        .wrapContentSize()
        .background(containerColor),
         shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(containerColor)
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
                    boltSize = 25.dp)
            }else{
                ApiImage(
                    context = context,
                    imageUri = character.image.toUri(),
                    imgHolder = R.drawable.profile_img,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(80.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = character.name,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(8.dp))
            // Ancestry
//            Text(
//                text = character.ancestry,
//                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
//                fontWeight = FontWeight.Normal,
//                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
//            )
            PillBtn(
                title = character.ancestry,
                startIcon = Icons.Filled.OpenWith,
                horizontalPadding = 8.dp,
                verticalPadding = 8.dp,
                onClick = onHouseClicked
            )
        }
    }
}
