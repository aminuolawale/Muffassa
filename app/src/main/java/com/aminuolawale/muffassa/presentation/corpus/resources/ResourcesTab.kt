package com.aminuolawale.muffassa.presentation.corpus.resources

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewState

@Composable
fun ResourcesTab(state: CorpusViewState) {
    state.resources.map {
        Text(it.name)
    }
}


@Composable
fun ResourceItem(state: CorpusViewState){
    Row() {

    }
}