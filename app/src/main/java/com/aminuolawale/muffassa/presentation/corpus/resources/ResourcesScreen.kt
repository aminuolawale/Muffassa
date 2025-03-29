package com.aminuolawale.muffassa.presentation.corpus.resources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewModel
import com.aminuolawale.muffassa.presentation.corpus.components.CorpusScaffold


@Composable
fun ResourcesScreen(navController: NavController, corpusViewModel: CorpusViewModel) {
    CorpusScaffold(navController, corpusViewModel) {
        val resourcesViewModel = corpusViewModel.resourcesViewModel
        resourcesViewModel.state.collectAsState().let {
            ResourcesList(
                state = it.value,
                onSelectionChange = { resourcesViewModel.onEvent(ResourcesEvent.SelectionChange(it)) },
                onDeleteClick = { resourcesViewModel.onEvent(ResourcesEvent.DeleteResources(it)) })
        }
    }
}