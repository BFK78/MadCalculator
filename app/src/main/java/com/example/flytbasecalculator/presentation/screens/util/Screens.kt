package com.example.flytbasecalculator.presentation.screens.util

import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val title: String, val route: String, val icon: ImageVector? = null) {


    object LoginScreen: Screens(title = "Login", route = "login_screen")
    object CalculatorScreen: Screens(title="calculator", route = "calculator_screen")

}
