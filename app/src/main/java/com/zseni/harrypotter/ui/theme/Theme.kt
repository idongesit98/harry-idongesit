package com.zseni.harrypotter.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColorScheme(
    primary = accent,
    secondary = TextColorWhite,
    tertiary = accentLight,
    onPrimary = BackgroundDark,
    onSecondary = BackgroundDarkAlt,
)

private val LightColorPalette = lightColorScheme(
    primary = accent,
    tertiary = accentLight,
    secondary = TextColorBlack,
    onPrimary = BackgroundLightAlt,
    onSecondary = BackgroundLight,
    surfaceTint = Purple700

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun HarryPotterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()
    val view = LocalView.current
    if (!view.isInEditMode){
        SideEffect {
            // change status bar background and foreground icon colours
            systemUiController.setStatusBarColor(
                color = colorScheme.onPrimary,
                darkIcons = !darkTheme
            )

            systemUiController.setNavigationBarColor(
                color = colorScheme.onPrimary,
                darkIcons = !darkTheme
            )
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}