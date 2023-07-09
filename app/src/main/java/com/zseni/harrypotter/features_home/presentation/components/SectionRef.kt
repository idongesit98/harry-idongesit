package com.zseni.harrypotter.features_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zseni.harrypotter.core.presentation.components.PillBtn
import com.zseni.hogwarts_school.R

@Composable
fun SectionRef(
    title:String,
    icon:ImageVector,
    onSeeAll: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
    ){

        //title
        Row(
            modifier = Modifier
            .wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically

        ){
            Box(
                 Modifier
                    .clip(CircleShape)
                    .size(45.dp)
                    .background(MaterialTheme.colorScheme.tertiary),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = icon,
                    contentDescription = stringResource(id = R.string.sect_icon),
                    tint = MaterialTheme.colorScheme.primary,


                )
            }
            Text(
                text = title,
                Modifier.weight(1f),
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
            )

            PillBtn(
                title = stringResource(id = R.string.view_all),
                textColour = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f ),
                endIcon = Icons.Outlined.ChevronRight ,
                containerColour = MaterialTheme.colorScheme.onPrimary ,
                horizontalPadding = 16.dp,
                verticalPadding = 8.dp,
                onClick = onSeeAll
            )


        }

    }

}
