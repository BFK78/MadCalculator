package com.example.flytbasecalculator.domain.usecase

import com.example.flytbasecalculator.data.remote.dto.Response
import com.example.flytbasecalculator.domain.repository.CalculatorRepository
import javax.inject.Inject

class DeleteExpressionUseCase @Inject constructor(
    private val repository: CalculatorRepository
) {

    suspend operator fun invoke(token: String): Response<Any> {
        return repository.deleteExpression(token = token)
    }

}