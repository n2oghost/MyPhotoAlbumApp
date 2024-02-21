package com.incentro.core_ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.incentro.core_ui.R

private val fontFamilyUnderway = FontFamily(
    listOf(
        Font(
            resId = R.font.underway_bold,
            weight = FontWeight.Bold
        )
    )
)

private val fontFamilyDccAsh = FontFamily(
    listOf(
        Font(
            resId = R.font.dcc_ash_regular
        )
    )
)

val typography = Typography(
    defaultFontFamily = fontFamilyDccAsh,
    h1 = TextStyle(
        fontFamily = fontFamilyUnderway,
        fontWeight = FontWeight.Light,
        fontSize = 28.sp,
        letterSpacing = (1.15).sp
    ),
    h2 = TextStyle(
        fontSize = 15.sp,
        letterSpacing = (1.15).sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    body1 = TextStyle(
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = (1.15).sp
    ),
    caption = TextStyle(
        fontFamily = fontFamilyUnderway,
        fontSize = 12.sp,
        letterSpacing = (1.15).sp
    ),
)