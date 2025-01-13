package com.aminuolawale.muffassa.presentation.corpus.snippets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewModel
import com.aminuolawale.muffassa.presentation.corpus.components.CorpusScaffold

@Composable
fun SnippetsScreen(navController: NavController, corpusViewModel: CorpusViewModel) {
    CorpusScaffold(navController, corpusViewModel) {

        Text("Snippets")
    }
}