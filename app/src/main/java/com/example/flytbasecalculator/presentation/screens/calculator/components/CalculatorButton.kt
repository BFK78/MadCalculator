package com.example.flytbasecalculator.presentation.screens.calculator.components

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flytbasecalculator.presentation.screens.calculator.viewmodel.CalculatorViewModel

@Composable
fun RowScope.CalculatorButton(
    modifier: Modifier = Modifier,
    content: String,
    bgColor: Color = Color(0XFF282B33),
    contentColor: Color = Color.White,
    weight: Float = 1f,
    viewModel: CalculatorViewModel
) {

    val mutableIndicationSource = remember {
        MutableInteractionSource()
    }

    Card(
        modifier = modifier
            .fillMaxHeight()
            .padding(4.dp)
            .clip(CircleShape)
            .weight(weight = weight)
            .background(bgColor)
            .shadow(8.dp)
            .clickable(interactionSource = mutableIndicationSource, indication = LocalIndication.current) {
                viewModel.onButtonClick(content = content, calculateColor = contentColor)
            },
        backgroundColor = bgColor,
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Center
        ) {

            Text(
                text = content,
                color = contentColor,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )

        }
    }

}
