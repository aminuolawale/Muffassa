package com.aminuolawale.muffassa.presentation.corpus.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aminuolawale.muffassa.presentation.corpus.ContentArea
import com.aminuolawale.muffassa.presentation.corpus.CorpusEditState
import com.aminuolawale.muffassa.presentation.corpus.CorpusEvent
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewModel
import com.aminuolawale.muffassa.presentation.corpus.DescriptionArea
import com.aminuolawale.muffassa.presentation.corpus.TitleArea

@Composable
fun HomeTab(viewModel: CorpusViewModel) {
    viewModel.state.collectAsState().let {state ->
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