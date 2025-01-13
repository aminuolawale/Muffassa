package com.aminuolawale.muffassa.presentation.corpus.quiz

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewModel
import com.aminuolawale.muffassa.presentation.corpus.components.CorpusScaffold


@Composable
fun QuizScreen(navController: NavController, corpusViewModel: CorpusViewModel) {
    CorpusScaffold(navController, corpusViewModel) {
        Text("Quiz")
    }
}