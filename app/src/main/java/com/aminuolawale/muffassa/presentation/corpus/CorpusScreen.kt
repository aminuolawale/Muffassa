package com.aminuolawale.muffassa.presentation.corpus

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.components.ContentWithBottomBar

@Composable
fun CorpusScreen(navController: NavController, corpusViewModel: CorpusViewModel) {
    corpusViewModel.state.collectAsState().let {
        ContentWithBottomBar(navController, corpusViewModel.userData) {
            Column {
                TextField(value = it.value.corpus?.title ?: "Untitled", {
                    corpusViewModel.onEvent(CorpusEvent.TitleChanged(it))
                })
            }
        }
    }
}