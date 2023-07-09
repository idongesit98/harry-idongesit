package com.zseni.harrypotter.core.presentation.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zseni.hogwarts_school.R

/**
 *Context is the current state of an app and let the
 * newly created objects to know what has been going on
 * that concerns it
 *
 * characterImage handles when the the image from the api is not loading
 * and when the app is just loading
 */
@Composable
fun CharacterImage(
    context: Context,
    imageSize: Dp = 120.dp,
    boltSize:Dp = 25.dp,
    boltIconSize:Dp = 18.dp,
    uri: Uri
){
    Box(modifier = Modifier
        .size(size = imageSize)
    ){
        
        ApiImage(
            context = context,
            imageUri = uri,
            imgHolder = R.drawable.profile_img,
            modifier = Modifier
                .clip(CircleShape)
                .size(size = imageSize)
                .border(
                    width = 4.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    shape = CircleShape
                ), 

        )
        
        Row(modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
        ){
            Box(modifier = Modifier
                .clip(CircleShape)
                .size(boltSize)
                .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Outlined.Bolt,
                    contentDescription = stringResource(id = R.string.img_desc),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(boltIconSize))
            }
        }

    }

}