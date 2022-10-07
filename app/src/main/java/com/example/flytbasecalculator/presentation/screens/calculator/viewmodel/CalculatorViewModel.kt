package com.example.flytbasecalculator.presentation.screens.calculator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flytbasecalculator.domain.usecase.DeleteExpressionUseCase
import com.example.flytbasecalculator.domain.usecase.GetAllExpressionUseCase
import com.example.flytbasecalculator.domain.usecase.InsertExpressionUseCase
import com.example.flytbasecalculator.util.Queue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val getAllExpressionUseCase: GetAllExpressionUseCase,
    private val insertExpressionUseCase: InsertExpressionUseCase,
    private val deleteExpressionUseCase: DeleteExpressionUseCase
): ViewModel() {

    var expressionQueue by mutableStateOf(Queue<String>())
        private set

    var calculate by mutableStateOf("")
        private set

    private var afterNumber by mutableStateOf(false)

    //button click function
    fun onButtonClick(content: String, calculateColor: Color) {

        when (calculateColor) {
            Color.White -> {
                calculate += if (content == ".") {
                    if (calculate.contains(".")) {
                        return
                    } else {
                        afterNumber = false
                        content
                    }
                } else {
                    afterNumber = true
                    content
                }
            }

            Color(0xFF26D4B6) -> {
                calculate = if (content == "AC") {
                    afterNumber = false
                    ""
                } else {
                    if (calculate.startsWith("-")) {
                        calculate.substring(1 until calculate.length)
                    } else {
                        if (calculate.isNotEmpty()) {
                            "-$calculate"
                        } else {
                            return
                        }
                    }
                }
            }

            Color(0xFFF37979) -> {
                if (afterNumber) {
                    if (content != "=") {
                        calculate += " $content "
                        afterNumber = false
                    } else {
                        calculate = calculateAnswer(calculate)
                    }
                }
            }
        }
    }

    fun calculateAnswer(value: String): String {

        val digits = value.split(" ") as MutableList
        multiply(digits)
        return digits[0]

    }

    private fun multiply(list: MutableList<String>) {
        val x = list.indexOf("x")
        if (x != -1) {
            val multiply = list[x - 1].toFloat() * list[x + 1].toFloat()
            list.removeAt(x)
            list.removeAt(x)
            list[x - 1] = multiply.toString()
            multiply(list)
        } else {
            addition(list)
        }
    }

    private fun addition(list: MutableList<String>) {
        val x = list.indexOf("+")
        if (x != -1) {
            val addition = list[x - 1].toFloat() + list[x + 1].toFloat()
            list.removeAt(x)
            list.removeAt(x)
            list[x - 1] = addition.toString()
            addition(list)
        } else {
            division(list)
        }
    }

    private fun subtract(list: MutableList<String>) {
        val x = list.indexOf("-")
        if (x != -1) {
            val addition = list[x - 1].toFloat() - list[x + 1].toFloat()
            list.removeAt(x)
            list.removeAt(x)
            list[x - 1] = addition.toString()
            subtract(list)
        } else {
            return
        }
    }

    private fun division(list: MutableList<String>) {
        val x = list.indexOf("/")
        if (x != -1) {
            val addition = list[x - 1].toFloat() / list[x + 1].toFloat()
            list.removeAt(x)
            list.removeAt(x)
            list[x - 1] = addition.toString()
            division(list)
        } else {
            subtract(list)
        }
    }

    fun getAllExpression(token: String) = viewModelScope.launch {
        val response = getAllExpressionUseCase(token = token)
        response.data?.calculations?.forEach {
            expressionQueue.enqueue(it.expression)
        }
    }

    fun insertExpression(queue: Queue<String>, token: String) = viewModelScope.launch {
        deleteExpressionUseCase(token)
        queue.queue.forEach {
            insertExpressionUseCase(expression = it, token = token)
        }
    }

}
