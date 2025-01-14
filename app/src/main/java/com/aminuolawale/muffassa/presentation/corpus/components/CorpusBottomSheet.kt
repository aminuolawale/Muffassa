package com.aminuolawale.muffassa.presentation.corpus.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aminuolawale.muffassa.presentation.components.TextFormField
import com.aminuolawale.muffassa.presentation.corpus.CorpusEvent
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CorpusBottomSheet(viewModel: CorpusViewModel) {
    val sheetState = rememberModalBottomSheetState()
    var state by remember { mutableStateOf(viewModel.state.value.corpus) }
    ModalBottomSheet(
        modifier = Modifier
            .fillMaxHeight(),
        onDismissRequest = { viewModel.onEvent(CorpusEvent.EndEdit) },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(1.dp))
                IconButton(onClick = { state?.let { viewModel.onEvent(CorpusEvent.Save(it)) } }) {
                    Icon(Icons.Default.Check, contentDescription = "Save")
                }
            }
            TextFormField(
                value = state?.title ?: "",
                errors = listOf(),
                label = { Text("Name") },
                onValueChange = { state = state?.copy(title = it) },
                onFocusChange = {}
            )
            TextFormField(
                value = state?.description ?: "",
                errors = listOf(),
                label = { Text("Description") },
                minLines = 2,
                onValueChange = { state = state?.copy(description = it) },
                onFocusChange = {}
            )
        }

    }
}