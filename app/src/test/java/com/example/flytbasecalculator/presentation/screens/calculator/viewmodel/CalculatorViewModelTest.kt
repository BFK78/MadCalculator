package com.example.flytbasecalculator.presentation.screens.calculator.viewmodel

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class CalculatorViewModelTest {

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun `Mad calculation`() {
        val expression = "2 + 6 x 5 - 8 + 7 / 3 x 4 / 9 + 1 - 5 x 8"
        val answer = viewModel.calculateAnswer(expression)
        assertThat(answer).isEqualTo("-8.125")

    }
}