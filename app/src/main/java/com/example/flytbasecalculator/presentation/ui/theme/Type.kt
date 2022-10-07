package com.example.flytbasecalculator.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White
    ),
    h1 = TextStyle(
        color = Color.White,
        fontFamily = poppins,
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold
    ),
    h5 = TextStyle(
       color = Color.White,
       fontFamily = poppins,
       fontSize = 24.sp,
       fontWeight = FontWeight.Medium
    ),
    h6 = TextStyle(
        fontSize = 20.sp,
        fontFamily = poppins,
        fontWeight = FontWeight.Medium,
        color = Color.White
    ),
    body2 = TextStyle(
        color = Color.White.copy(
            alpha = 0.6f
        ),
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = poppins
    ),
    button = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = poppins
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)