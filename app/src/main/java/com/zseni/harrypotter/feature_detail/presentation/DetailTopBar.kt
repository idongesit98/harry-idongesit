package com.zseni.harrypotter.feature_detail.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.zseni.hogwarts_school.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    title:String,
    onBackPressed:()->Unit
){
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.ExtraBold
            )
        },

        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_icon),
                    tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
                )
            }
        },

        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.secondary
        ),

        actions = {
            IconButton(onClick = {}){
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = stringResource(id = R.string.more)
                )
            }
        }
    )
}
