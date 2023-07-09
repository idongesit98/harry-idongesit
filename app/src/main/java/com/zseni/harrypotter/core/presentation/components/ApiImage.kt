package com.zseni.harrypotter.core.presentation.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zseni.hogwarts_school.R

/**
 * This file determine if the placeholder shows when the image from
 * the Api is unavailable for it to display.
 */
@Composable
fun ApiImage(
    modifier:Modifier = Modifier,
    context: Context,
    imageUri: Uri?,
    imgHolder: Int =  R.drawable.profile_img
){
    if (imageUri?.toString()?.isBlank() == true || imageUri == null){
        Image(
            painter = painterResource( id = R.drawable.profile_img),
            contentDescription = stringResource(id = R.string.placeholder_img),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(CircleShape)

        )
    }else{
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imageUri)
                .crossfade(true)
                .placeholder(imgHolder)
                .build(),
            contentDescription = stringResource(id = R.string.wizard_img),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
        )
    }
}