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
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.components.BottomBarState
import com.aminuolawale.muffassa.presentation.components.ContentWithBottomBar
import com.aminuolawale.muffassa.presentation.components.MuffassaScaffold

@Composable
fun CorpusScreen(navController: NavController, corpusViewModel: CorpusViewModel) {
    corpusViewModel.state.collectAsState().let {
        MuffassaScaffold(screen = Screen.Corpus,
            onClick = { corpusViewModel.onEvent(CorpusEvent.EndEdit) },
            onNavigationIconClick = {navController.popBackStack()},
            onResourceClick = { corpusViewModel.onEvent(CorpusEvent.SelectTab(CorpusTab.RESOURCES)) },
            onArtefactClick = { corpusViewModel.onEvent(CorpusEvent.SelectTab(CorpusTab.ARTEFACTS)) }) {
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
                        text = it.value.corpus?.title ?: "Untitled",
                        isEditing = it.value.editState == CorpusEditState.TITLE,
                        onClick = {
                            corpusViewModel.onEvent(
                                CorpusEvent.BeginEdit(
                                    CorpusEditState.TITLE
                                )
                            )
                        },
                        onValueChange = { value ->
                            corpusViewModel.onEvent(
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
                        text = it.value.corpus?.description ?: "Description",
                        isEditing = it.value.editState == CorpusEditState.DESCRIPTION,
                        onClick = { corpusViewModel.onEvent(CorpusEvent.BeginEdit(CorpusEditState.DESCRIPTION)) },
                        onValueChange = { corpusViewModel.onEvent(CorpusEvent.DescriptionChanged(it)) })
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    ContentArea(it.value.activeTab)
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
fun ContentArea(activeTab: CorpusTab) {
    when (activeTab) {
        CorpusTab.RESOURCES -> {
            Text("Resources")
        }

        CorpusTab.ARTEFACTS -> {
            Text("Artefacts")
        }
    }
}