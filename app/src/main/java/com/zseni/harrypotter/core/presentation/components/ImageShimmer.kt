package com.zseni.harrypotter.core.presentation.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun ImageShimmer(
    isLoading:Boolean,
    contentAfterLoading: @Composable ()-> Unit,
    modifier: Modifier = Modifier
){
    if (isLoading){
       LazyRow(
           content = {
               items(5){
                   Column(modifier= modifier
                       .fillMaxWidth()
                       .wrapContentHeight()
                       //.shimmerEffect()
                       //.background(MaterialTheme.colorScheme.)
                       .padding(10.dp),
                   horizontalAlignment = Alignment.CenterHorizontally,
                   verticalArrangement = Arrangement.Center
                   ){
                       Box(
                           modifier = Modifier
                           .clip(CircleShape)
                           //.background(MaterialTheme.colorScheme.tertiary)
                           .size(80.dp)
                               .shimmerEffect()
                       )

                       Spacer(modifier = Modifier.height(8.dp))

                       Box(modifier = Modifier
                           .clip(RoundedCornerShape(16.dp))
                           //.background(MaterialTheme.colorScheme.tertiary)
                           .width(120.dp)
                           .height(20.dp)
                           .shimmerEffect())


                       Spacer(modifier = Modifier.height(16.dp))

                       Box(
                           modifier = Modifier
                               .clip(RoundedCornerShape(16.dp))
                              // .background(MaterialTheme.colorScheme.secondary)
                               .width(120.dp)
                               .height(20.dp)
                               .shimmerEffect())
                   }
               }
               
           },
           modifier = Modifier
               .fillMaxWidth()
               .height(250.dp),
           horizontalArrangement = Arrangement.spacedBy(16.dp),
           state = rememberLazyListState()
       )
    }else{
        contentAfterLoading()
    }

}

fun Modifier.shimmerEffect():Modifier = composed{
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFF1E5F1),
                Color(0xFFDABFDA),
                Color(0xFFCAB7CA)
            ),
            start = Offset(startOffsetX,0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }

}