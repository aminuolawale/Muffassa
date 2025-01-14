package com.aminuolawale.muffassa.presentation.newresource.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusState
import com.aminuolawale.muffassa.domain.model.ResourceType
import com.aminuolawale.muffassa.presentation.components.TextFormField
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
fun NameField(
    state: NewResourceViewState,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit
) {
    TextFormField(
        value = state.resource?.name ?: "",
        errors = state.errors[FormField.NAME] ?: listOf(),
        label = { Text("Name") },
        onValueChange = onValueChange,
        onFocusChange = onFocusChange
    )
}


@Composable
fun DescriptionField(state: NewResourceViewState, onValueChange: (String) -> Unit) {
    TextFormField(
        value = state.resource?.description ?: "",
        errors = state.errors[FormField.DESCRIPTION] ?: listOf(),
        label = { Text("Description") },
        minLines = 2,
        onValueChange = onValueChange,
        onFocusChange = {})
}

@Composable
fun NoteDataField(
    state: NewResourceViewState,
    onChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit
) {
    TextFormField(
        value = state.resource?.data?.value ?: "",
        errors = state.errors[FormField.DATA] ?: listOf(),
        label = { Text("Note") },
        minLines = 5,
        onValueChange = onChange,
        onFocusChange = onFocusChange
    )
}


