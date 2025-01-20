package com.aminuolawale.muffassa.presentation.corpus.resources

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewModel
import com.aminuolawale.muffassa.presentation.corpus.components.CorpusScaffold


@Composable
fun ResourcesScreen(navController: NavController, corpusViewModel: CorpusViewModel) {
    CorpusScaffold(navController, corpusViewModel) {
        ResourcesList(corpusViewModel.resourcesViewModel)
    }
}