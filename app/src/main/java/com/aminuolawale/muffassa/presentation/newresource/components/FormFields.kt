package com.aminuolawale.muffassa.presentation.newresource.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aminuolawale.muffassa.domain.model.ResourceType
import com.aminuolawale.muffassa.presentation.newresource.FormField
import com.aminuolawale.muffassa.presentation.newresource.NewResourceViewState

@Composable
fun ResourceTypeSelector(
    state: NewResourceViewState,
    onNoteClick: () -> Unit,
    onArticleClick: () -> Unit,
    onFileClick: () -> Unit
) {
    Row {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = state.type == ResourceType.NOTE,
                onClick = onNoteClick
            )
            Text("Note")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = state.type == ResourceType.ARTICLE,
                onClick = onArticleClick
            )
            Text("Article")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = state.type == ResourceType.FILE,
                onClick = onFileClick
            )
            Text("File")
        }
    }
}

@Composable
fun BaseFormField(content: @Composable () -> Unit) {
    Column(horizontalAlignment = Alignment.Start) {
        content()
    }
}

@Composable
fun NameField(
    state: NewResourceViewState,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit
) {
    BaseFormField {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChange(it) },
            value = state.resource?.name ?: "",
            onValueChange = onValueChange,
            label = { Text("Name") },
            shape = RoundedCornerShape(20.dp),
            isError = state.errors[FormField.NAME]?.isNotEmpty() ?: false
        )
        FormFieldError(state = state, formField = FormField.NAME)
    }
}


@Composable
fun DescriptionField(state: NewResourceViewState, onValueChange: (String) -> Unit) {
    BaseFormField {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.resource?.description ?: "",
            onValueChange = onValueChange,
            label = { Text("Description") },
            minLines = 2,
            shape = RoundedCornerShape(20.dp),
        )
    }
}

@Composable
fun NoteDataField(
    state: NewResourceViewState,
    onChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit
) {
    BaseFormField {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {onFocusChange(it) },
            value = state.resource?.data?.value ?: "",
            onValueChange = onChange,
            label = { Text("Note") },
            minLines = 5,
            shape = RoundedCornerShape(20.dp),
            isError = state.errors[FormField.DATA]?.isNotEmpty() ?: false
        )
        FormFieldError(state = state, formField = FormField.DATA)
    }
}

@Composable
fun FormFieldError(state: NewResourceViewState, formField: FormField) {
    state.errors[formField]?.map {
        Text(text = it, color = Color.Red)
    }
}

