package com.example.flytbasecalculator.domain.usecase

import com.example.flytbasecalculator.data.remote.dto.ExpressionDto
import com.example.flytbasecalculator.data.remote.dto.Response
import com.example.flytbasecalculator.domain.repository.CalculatorRepository
import javax.inject.Inject

class GetAllExpressionUseCase @Inject constructor(
    private val calculatorRepository: CalculatorRepository
) {

    suspend operator fun invoke(token: String): Response<ExpressionDto> {
       return calculatorRepository.getAllExpression(token)
    }

}