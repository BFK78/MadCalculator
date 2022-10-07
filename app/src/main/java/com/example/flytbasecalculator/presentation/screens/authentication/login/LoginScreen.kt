package com.example.flytbasecalculator.presentation.screens.authentication.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.flytbasecalculator.R
import com.example.flytbasecalculator.presentation.screens.authentication.viewmodel.AuthenticationViewModel
import com.example.flytbasecalculator.presentation.screens.components.ButtonComponent
import com.example.flytbasecalculator.presentation.screens.components.LoadingDialog
import com.example.flytbasecalculator.presentation.screens.components.TextFieldComponent
import com.example.flytbasecalculator.presentation.screens.util.Screens
import com.example.flytbasecalculator.presentation.screens.util.checkEmail
import com.example.flytbasecalculator.presentation.screens.util.checkPassword
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    viewModel: AuthenticationViewModel = hiltViewModel(),
) {

    val scope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    val email = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    val emailError = remember {
        mutableStateOf(false)
    }

    val passwordError = remember {
        mutableStateOf(false)
    }

    val openDialog = remember {
        mutableStateOf(false)
    }

    val state = viewModel.loginState

    LaunchedEffect(key1 = email.value) {
        emailError.value = !email.value.checkEmail() && email.value.isNotEmpty()
    }

    LaunchedEffect(key1 = password.value) {
        passwordError.value = !password.value.checkPassword() && password.value.isNotEmpty()
    }

    LaunchedEffect(key1 = state.status, key2 = state.loading, key3 = state.message) {
        openDialog.value = state.loading
        if (!state.status && state.message != null) {
            scaffoldState.snackbarHostState.showSnackbar(message = state.message, duration = SnackbarDuration.Long)
        }

        if (state.status) {
            navHostController.navigate(Screens.CalculatorScreen.route + "/${state.data?.token}")
        }
    }


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {

                },
                backgroundColor = Color.Black
            )
        },
        modifier = Modifier
            .padding(horizontal = 32.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            if (openDialog.value) {
                LoadingDialog(openDialog = openDialog)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .verticalScroll(state = rememberScrollState()),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        text = stringResource(id = R.string.login_heading),
                        style = MaterialTheme.typography.h1
                    )

                    Text(
                        text = stringResource(id = R.string.login_subheading),
                        style = MaterialTheme.typography.h1,
                        fontWeight = FontWeight.Normal,
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    TextFieldComponent(
                        textState = email,
                        modifier = Modifier.fillMaxWidth(),
                        labelText = R.string.login_email_placeholder,
                        error = emailError
                    )

                    AnimatedVisibility(visible = emailError.value) {

                        Text(
                            text = stringResource(id = R.string.error_email),
                            color = MaterialTheme.colors.error,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    TextFieldComponent(
                        textState = password,
                        modifier = Modifier.fillMaxWidth(),
                        labelText = R.string.login_password_placeholder,
                        error = passwordError
                    )

                    AnimatedVisibility(visible = passwordError.value) {

                        Text(
                            text = stringResource(id = R.string.error_password),
                            color = MaterialTheme.colors.error,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                }
                Column {

                    Spacer(modifier = Modifier.height(16.dp))

                    ButtonComponent(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = Color.White,
                        shape = RoundedCornerShape(10.dp),
                        text = R.string.login_button,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        if (!emailError.value && !passwordError.value && email.value.isNotEmpty() && password.value.isNotEmpty()) {
                            viewModel.loginUser(email = email.value, password = password.value)
                        } else {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(message = "Enter valid inputs")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}