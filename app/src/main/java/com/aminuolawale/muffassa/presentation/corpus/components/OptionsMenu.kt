package com.aminuolawale.muffassa.presentation.corpus.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewState


@Composable
fun OptionsMenu(state: CorpusViewState, onDeleteClick: () -> Unit, onDismissRequest: () -> Unit) {
    DropdownMenu(expanded = state.optionsMenu, onDismissRequest = onDismissRequest) {
        DropdownMenuItem(text = { Text("Edit") }, leadingIcon = {
            Icon(Icons.Default.Edit, contentDescription = "Edit corpus")
        }, onClick = onDeleteClick)
    }
}