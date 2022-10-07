package com.example.flytbasecalculator.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.flytbasecalculator.presentation.screens.authentication.login.LoginScreen
import com.example.flytbasecalculator.presentation.screens.calculator.CalculatorScreen
import com.example.flytbasecalculator.presentation.screens.util.Screens
import com.example.kalakarwallet.wallet_feature.presentation.util.Constants.AUTHENTICATION_ROUTE



fun NavGraphBuilder.applicationNavigation(
    navHostController: NavHostController
) {

    navigation(
        startDestination = Screens.LoginScreen.route,
        route = AUTHENTICATION_ROUTE
    ) {

        composable(
            route = Screens.LoginScreen.route,

        ) {
            LoginScreen(
                navHostController = navHostController
            )
        }

        composable(
            route = Screens.CalculatorScreen.route + "/{token}",
            arguments = listOf(navArgument(name = "token") { type = NavType.StringType })
        ) {
            val token = it.arguments!!.getString("token", "")
            CalculatorScreen(token = token)
        }
    }
}
