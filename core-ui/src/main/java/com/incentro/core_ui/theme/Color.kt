package com.incentro.core_ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val White = Color.White
val Black = Color.Black
val White150 = Color.White.copy(alpha = 0.15f)
val White980 = Color.White.copy(alpha = 0.98f).compositeOver(Color.Black)
val OffBlack = Color(0xFF1C1B1F)
val OffWhite = Color(0xFFFFFBFE)
val Grey900 = Color(0xFF333333)

val LightColorScheme = lightColors(
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

val DarkColorScheme = darkColors(
    primary = Purple80,
    primaryVariant = Pink80,
    secondary = PurpleGrey80,
    background = Grey900,
    surface = White150,
    onPrimary = Black,
    onSecondary = Black,
    onBackground = OffWhite,
    onSurface = White980,
)