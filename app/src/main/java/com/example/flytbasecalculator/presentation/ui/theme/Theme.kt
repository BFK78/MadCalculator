package com.example.flytbasecalculator.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.flytbasecalculator.R
import com.example.flytbasecalculator.presentation.ui.theme.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = green,
    primaryVariant = Purple700,
    secondary = gray,
    background = black,
    error = error
)

private val LightColorPalette = lightColors(
    primary = green,
    primaryVariant = Purple700,
    secondary = gray,
    background = black,
    error = error

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

val poppins = FontFamily(
    Font(
        R.font.poppinst_medium,
        FontWeight.Medium,
    ),
    Font(
        R.font.poppins_black,
        FontWeight.Black
    ),
    Font(
        R.font.poppins_bold,
        FontWeight.Bold
    ),
    Font(
        R.font.poppins_light,
        FontWeight.Light
    ),
    Font(
        R.font.poppins_extra_bold,
        FontWeight.ExtraBold
    ),
    Font(
        R.font.poppins_extra_light,
        FontWeight.ExtraLight
    ),
    Font(
        R.font.poppins_regular,
        FontWeight.Normal
    ),
    Font(
        R.font.poppins_semi_bold,
        FontWeight.SemiBold
    ),
    Font(
        R.font.poppinss_thin,
        FontWeight.Thin
    )
)

@Composable
fun FlytBaseCalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(Color.Black)

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}