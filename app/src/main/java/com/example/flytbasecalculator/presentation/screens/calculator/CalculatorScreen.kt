package com.example.flytbasecalculator.presentation.screens.calculator

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flytbasecalculator.presentation.screens.calculator.components.CalculatorButton
import com.example.flytbasecalculator.presentation.screens.calculator.viewmodel.CalculatorViewModel
import com.example.flytbasecalculator.util.Queue

var resultMap = Queue<String>()

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = hiltViewModel(),
    token: String
) {

    val context = LocalContext.current

    val expressionQueue = viewModel.expressionQueue

    val calculate = viewModel.calculate

    val fetched = remember {
        mutableStateOf(false)
    }

    val expression = remember {
        mutableStateOf("")
    }

    val value = remember {
        mutableStateOf("")
    }

    val historyButton = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        viewModel.getAllExpression(token)
        Toast.makeText(context, "Fetched from the cloud.", Toast.LENGTH_LONG).show()

    }

    LaunchedEffect(key1 = expressionQueue) {
        resultMap = expressionQueue
        fetched.value = true
    }


    LaunchedEffect(key1 = calculate) {
        if (expression.value.isEmpty() || calculate.length > expression.value.length) {
            expression.value = calculate
        } else if (expression.value.length > calculate.length && calculate.isNotEmpty()) {
            if (fetched.value) {
                resultMap = Queue()
                fetched.value = false
            }
            value.value = calculate
            if (resultMap.size >= 10) {
                resultMap.dequeue()
            }
            resultMap.enqueue("${expression.value} = ${value.value}")
            expression.value = ""
            value.value = ""
        } else {
            expression.value = ""
            value.value = ""
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
           TopAppBar(
               title = {
                   Text(text = "")
               },
               navigationIcon = {

               },
               actions = {
                   if (historyButton.value) {
                       ClickableText(
                           text = buildAnnotatedString {
                               withStyle(style = SpanStyle(
                                   color = Color.White,
                                   fontWeight = FontWeight.Bold,
                                   fontSize = 18.sp,
                               )) {
                                   append("Save to cloud")
                               }
                           },
                           onClick = {
                               viewModel.insertExpression(resultMap, token)
                               Toast.makeText(context, "Inserted to the cloud successfully", Toast.LENGTH_LONG).show()
                               historyButton.value = !historyButton.value
                           },
                           modifier = Modifier
                               .padding(horizontal = 16.dp)
                               .clip(shape = RoundedCornerShape(10.dp))
                               .background(Color(0XFF282B33))
                               .padding(horizontal = 16.dp, vertical = 8.dp)
                       )
                   }

                   ClickableText(
                       text = buildAnnotatedString { withStyle(style = SpanStyle(
                           color = Color.White,
                           fontWeight = FontWeight.Bold,
                           fontSize = 18.sp,
                       )) {
                           append("History")
                       } },
                       onClick = {
                           historyButton.value = !historyButton.value
                       },
                       modifier = Modifier
                           .padding(horizontal = 8.dp)
                           .clip(shape = RoundedCornerShape(10.dp))
                           .background(Color(0XFF282B33))
                           .padding(
                               horizontal = 16.dp,
                               vertical = 8.dp
                           )
                   )
               },
               backgroundColor = Color.Black
           )
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .animateContentSize()
        ) {

            if (historyButton.value) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .animateContentSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {

                    Spacer(modifier = Modifier.height(16.dp))

                    resultMap.EachFor {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = End
                        ) {
                            Text(
                                text = it,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            } else {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {

                    Text(
                        text = calculate,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(vertical = 16.dp, horizontal = 8.dp),
                        fontSize = 34.sp,
                        color = Color.White,
                        textAlign = TextAlign.End
                    )
                }
            }

            Card(
                modifier = Modifier
                    .weight(2f)
                    .clip(shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp))
                    .background(color = MaterialTheme.colors.secondary)
                    .shadow(elevation = 15.dp),
                backgroundColor = Color.Black.copy(alpha = 0.5f)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 32.dp, horizontal = 16.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {

                        CalculatorButton(
                            content = "AC",
                            contentColor = Color(0xFF26D4B6),
                            weight = 2f,
                            viewModel = viewModel
                        )

                        CalculatorButton(
                            content = "+/-",
                            weight = 1f,
                            contentColor = Color(0xFF26D4B6),
                            viewModel = viewModel
                        )

                        CalculatorButton(
                            content = "/",
                            weight = 1f,
                            contentColor = Color(0xFFF37979),
                            viewModel = viewModel
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {

                        CalculatorButton(
                            content = "7",
                            weight = 1f,
                            contentColor = Color.White,
                            viewModel = viewModel
                        )

                        CalculatorButton(
                            content = "8",
                            weight = 1f,
                            contentColor = Color.White,
                            viewModel = viewModel
                        )

                        CalculatorButton(
                            content = "9",
                            weight = 1f,
                            contentColor = Color.White,
                            viewModel = viewModel
                        )

                        CalculatorButton(
                            content = "x",
                            weight = 1f,
                            contentColor = Color(0xFFF37979),
                            viewModel = viewModel
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {

                        CalculatorButton(
                            content = "4",
                            weight = 1f,
                            contentColor = Color.White,
                            viewModel = viewModel
                        )

                        CalculatorButton(
                            content = "5",
                            weight = 1f,
                            contentColor = Color.White,
                            viewModel = viewModel
                        )

                        CalculatorButton(
                            content = "6",
                            weight = 1f,
                            contentColor = Color.White,
                            viewModel = viewModel
                        )

                        CalculatorButton(
                            content = "-",
                            weight = 1f,
                            contentColor = Color(0xFFF37979),
                            viewModel = viewModel
                        )

                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {

                        CalculatorButton(
                            content = "1",
                            weight = 1f,
                            contentColor = Color.White,
                            viewModel = viewModel
                        )

                        CalculatorButton(
                            content = "2",
                            weight = 1f,
                            contentColor = Color.White,
                            viewModel = viewModel
                        )

                        CalculatorButton(
                            content = "3",
                            weight = 1f,
                            contentColor = Color.White,
                            viewModel = viewModel
                        )

                        CalculatorButton(
                            content = "+",
                            weight = 1f,
                            contentColor = Color(0xFFF37979),
                            viewModel = viewModel
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {

                        CalculatorButton(
                            content = "0",
                            weight = 2f,
                            contentColor = Color.White,
                            viewModel = viewModel
                        )

                        CalculatorButton(
                            content = ".",
                            weight = 1f,
                            contentColor = Color.White,
                            viewModel = viewModel
                        )

                        CalculatorButton(
                            content = "=",
                            weight = 1f,
                            contentColor = Color(0xFFF37979),
                            viewModel = viewModel
                        )
                    }

                }
            }
        }
    }
}