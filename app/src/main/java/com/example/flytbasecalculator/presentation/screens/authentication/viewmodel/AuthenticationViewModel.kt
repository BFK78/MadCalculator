package com.example.flytbasecalculator.presentation.screens.authentication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flytbasecalculator.presentation.screens.authentication.util.AuthState
import com.example.flytbasecalculator.domain.usecase.AuthenticationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase
): ViewModel() {

    var loginState by mutableStateOf(AuthState())
        private set


    fun loginUser(email: String, password: String) = viewModelScope.launch {

        loginState = loginState.copy(
            loading = true
        )

        val response = authenticationUseCase.loginUser(email = email, password = password)

        loginState = loginState.copy(
            loading = false,
            status = response.status,
            message = response.message,
            data = response.data
        )
    }


}