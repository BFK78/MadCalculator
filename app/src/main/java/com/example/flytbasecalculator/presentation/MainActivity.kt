package com.example.flytbasecalculator.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.flytbasecalculator.presentation.navigation.applicationNavigation
import com.example.flytbasecalculator.presentation.screens.calculator.CalculatorScreen
import com.example.flytbasecalculator.presentation.screens.calculator.viewmodel.CalculatorViewModel
import com.example.flytbasecalculator.presentation.ui.theme.FlytBaseCalculatorTheme
import com.example.kalakarwallet.wallet_feature.presentation.util.Constants.AUTHENTICATION_ROUTE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<CalculatorViewModel>()

        setContent {
            FlytBaseCalculatorTheme {

                val navHostController = rememberNavController()

                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    ApplicationNavigation(navHostController = navHostController)
                }
            }
        }
    }
}

@Composable
fun ApplicationNavigation(
    navHostController: NavHostController
) {

    NavHost(navController = navHostController, startDestination = AUTHENTICATION_ROUTE) {
        applicationNavigation(navHostController = navHostController)
    }

}
