package com.zseni.harrypotter.features_home.presentation.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.twotone.Token
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.zseni.hogwarts_school.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    onSearch: ()->Unit,
    onMore: ()-> Unit
){

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.hogwarts),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.ExtraBold
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.secondary
        ),
       actions = {
         IconButton(onClick = onSearch) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id =R.string.search )
            )
         }

        IconButton(onClick = onMore){
            Icon(
                imageVector = Icons.TwoTone.Token,
                contentDescription = stringResource(id = R.string.more),
                tint = MaterialTheme.colorScheme.error
            )
         }
       }
    )
}
@Preview
@Composable
fun AppBarPreview(){
    HomeTopAppBar(
        onSearch = { /*TODO*/ }){
    }

}
