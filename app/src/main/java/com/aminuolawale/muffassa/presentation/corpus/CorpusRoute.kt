package com.aminuolawale.muffassa.presentation.corpus

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CorpusRoute(navController: NavController, corpusViewModel: CorpusViewModel, corpusId: Long?) {
    corpusViewModel.initialize(corpusId)
    CorpusScreen(navController, corpusViewModel)
}