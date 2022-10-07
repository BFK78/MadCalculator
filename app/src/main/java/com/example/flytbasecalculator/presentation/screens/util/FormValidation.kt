package com.example.flytbasecalculator.presentation.screens.util

import android.text.TextUtils
import android.util.Patterns

fun String.checkEmail(): Boolean {
    return !TextUtils.isEmpty(this) &&
            Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.checkPassword(): Boolean {
    return !TextUtils.isEmpty(this) &&
            this.contains(regex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"))
}
