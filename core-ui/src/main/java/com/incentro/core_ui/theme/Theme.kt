package com.incentro.core_ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColors(
    primary = Purple40,
    primaryVariant = Pink40,
    secondary = PurpleGrey40,
    background = OffWhite,
    surface = White980,
    onPrimary = White,
    onSecondary = White,
    onBackground = OffBlack,
    onSurface = OffBlack,
)

private val DarkColorScheme = darkColors(
    primary = Purple80,
    primaryVariant = Pink80,
    secondary = PurpleGrey80,
    background = Grey900,
    surface = White150,
    onPrimary = Grey900,
    onSecondary = Grey900,
    onBackground = OffWhite,
    onSurface = White980,
)

@Composable
fun ComposeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.secondary.toArgb()
            WindowCompat
                .getInsetsController((view.context as Activity).window, view)
                .isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colors = colorScheme,
        typography = typography,
        content = content,
        shapes = shapes
    )
}