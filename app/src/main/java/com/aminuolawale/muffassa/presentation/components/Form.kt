package com.aminuolawale.muffassa.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun TextFormField(
    value: String,
    errors: List<String>,
    label : @Composable () -> Unit,
    minLines: Int = 1,
    onValueChange: (String) -> Unit = {},
    onFocusChange: (FocusState) -> Unit = {},
) {
    Column(horizontalAlignment = Alignment.Start) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChange(it) },
            value = value,
            onValueChange = onValueChange,
            label = label,
            minLines = minLines,
            shape = RoundedCornerShape(20.dp),
            isError = errors.isNotEmpty()
        )
        errors.map {
            Text(text = it, color = Color.Red)
        }
    }
}