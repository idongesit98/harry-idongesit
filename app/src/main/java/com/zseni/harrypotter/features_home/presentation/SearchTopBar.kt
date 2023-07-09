package com.zseni.harrypotter.features_home.presentation


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.zseni.hogwarts_school.R

@Composable
fun SearchTopBar(
    onQueryInput:(query:String) -> Unit
){
    var query by remember{
        mutableStateOf("")
    }

    TextField(
        value = query,
        onValueChange = {newQuery ->
            query = newQuery
            onQueryInput(query)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Search,
                contentDescription = stringResource(id = R.string.search),
                tint = MaterialTheme.colors.secondary.copy(alpha = 0.8f )
            )
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.
            primaryVariant.copy(alpha = 0.3f),
            unfocusedIndicatorColor = MaterialTheme.colors.onPrimary,
            focusedIndicatorColor = MaterialTheme.colors.onPrimary,
            leadingIconColor = MaterialTheme.colors.primary,
            cursorColor = MaterialTheme.colors.secondary,
            textColor = MaterialTheme.colors.secondary
        ),
        placeholder = {
            Text(text = stringResource(id = R.string.search_hint))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false
        ),
        modifier = Modifier
            .clip(CircleShape)
            .fillMaxWidth(0.8f)
            .wrapContentHeight()
    )

}