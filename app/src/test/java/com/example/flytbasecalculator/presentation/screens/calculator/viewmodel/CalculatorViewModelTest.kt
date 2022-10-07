package com.example.flytbasecalculator.presentation.screens.calculator.viewmodel

import com.example.flytbasecalculator.data.repository.CalculatorRepositoryTest
import com.example.flytbasecalculator.domain.usecase.DeleteExpressionUseCase
import com.example.flytbasecalculator.domain.usecase.GetAllExpressionUseCase
import com.example.flytbasecalculator.domain.usecase.InsertExpressionUseCase
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class CalculatorViewModelTest {

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {

        val repositoryTest = CalculatorRepositoryTest()

        viewModel = CalculatorViewModel(
            GetAllExpressionUseCase(repositoryTest),
            InsertExpressionUseCase(repositoryTest),
            DeleteExpressionUseCase(repositoryTest)
        )
    }

    @Test
    fun `Mad calculation`() {
        val expression = "2 + 6 x 5 - 8 + 7 / 3 x 4 / 9 + 1 - 5 x 8"
        val answer = viewModel.calculateAnswer(expression)
        assertThat(answer).isEqualTo("-8.125")

    }
}