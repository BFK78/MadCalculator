package com.example.flytbasecalculator.presentation.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = Color.White,
    shape: RoundedCornerShape = RoundedCornerShape(0.dp),
    text: Int,
    style: TextStyle = MaterialTheme.typography.button,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = shape
    ) {
        Text(
            text = stringResource(id = text),
            style = style
        )
    }
}

@Composable
fun TextFieldComponent(
    modifier: Modifier = Modifier,
    textState: MutableState<String>,
    labelText: Int,
    error: State<Boolean> = mutableStateOf(false),
    singleLine: Boolean = true
) {

    OutlinedTextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
        },
        label = {
            Text(text = stringResource(id = labelText), color = if (error.value) MaterialTheme.colors.error else MaterialTheme.typography.body2.color)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White.copy(
                alpha = 0.1f
            ),
            unfocusedIndicatorColor = Color.White.copy(alpha = 0.2f),
            errorLabelColor = MaterialTheme.colors.error,

            ),
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        isError = error.value,
        singleLine = singleLine
    )
}

@Composable
fun LoadingDialog(
    openDialog: MutableState<Boolean>,
) {

    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false },
            properties = DialogProperties(dismissOnBackPress = false,
                dismissOnClickOutside = false)) {

            Box(
                modifier = Modifier
                    .size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colors.primary)
            }
        }
    }
}
