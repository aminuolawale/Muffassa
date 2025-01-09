package com.aminuolawale.muffassa.presentation.corpus

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.corpus.components.CorpusScaffold

@Composable
fun CorpusScreen(navController: NavController, viewModel: CorpusViewModel) {
    viewModel.state.collectAsState().let { state ->
        CorpusScaffold(navController, viewModel) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp, 60.dp, 20.dp, 0.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TitleArea(
                        text = state.value.corpus?.title ?: "Untitled",
                        isEditing = state.value.editState == CorpusEditState.TITLE,
                        onClick = {
                            viewModel.onEvent(
                                CorpusEvent.BeginEdit(
                                    CorpusEditState.TITLE
                                )
                            )
                        },
                        onValueChange = { value ->
                            viewModel.onEvent(
                                CorpusEvent.TitleChanged(
                                    value
                                )
                            )
                        })

                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DescriptionArea(modifier = Modifier.fillMaxWidth(),
                        text = state.value.corpus?.description ?: "Description",
                        isEditing = state.value.editState == CorpusEditState.DESCRIPTION,
                        onClick = { viewModel.onEvent(CorpusEvent.BeginEdit(CorpusEditState.DESCRIPTION)) },
                        onValueChange = { viewModel.onEvent(CorpusEvent.DescriptionChanged(it)) })
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    ContentArea()
                }
            }
        }
    }
}

@Composable
fun TitleArea(
    text: String,
    isEditing: Boolean,
    onClick: () -> Unit,
    onValueChange: (value: String) -> Unit
) {
    if (isEditing) {
        TextField(modifier = Modifier.padding(0.dp),
            value = text,
            textStyle = TextStyle(fontSize = 36.sp),
            onValueChange = { onValueChange.invoke(it) })
    } else {
        Text(
            text = text,
            fontSize = 36.sp,
            modifier = Modifier.clickable {
                onClick.invoke()
            })
    }
}

@Composable
fun DescriptionArea(
    modifier: Modifier,
    text: String,
    isEditing: Boolean,
    onClick: () -> Unit,
    onValueChange: (value: String) -> Unit
) {
    if (isEditing) {
        TextField(modifier = modifier, value = text, onValueChange = { onValueChange.invoke(it) })
    } else {
        Text(text = text, modifier = modifier.clickable { onClick.invoke() })
    }
}

@Composable
fun ContentArea() {
    Text("Artefacts")
}