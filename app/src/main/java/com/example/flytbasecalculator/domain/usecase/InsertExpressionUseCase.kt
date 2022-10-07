package com.example.flytbasecalculator.domain.usecase

import com.example.flytbasecalculator.data.remote.dto.Response
import com.example.flytbasecalculator.domain.repository.CalculatorRepository
import javax.inject.Inject

class InsertExpressionUseCase @Inject constructor(
    private val calculatorRepository: CalculatorRepository
) {

    suspend operator fun invoke(expression: String, token: String): Response<Any> {
        return calculatorRepository.insertExpression(expression = expression, token = token)
    }

}