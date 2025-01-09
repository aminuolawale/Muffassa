package com.aminuolawale.muffassa.presentation.corpus.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.components.MuffassaScaffold
import com.aminuolawale.muffassa.presentation.corpus.CorpusEvent
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewModel


@Composable
fun CorpusScaffold(navController: NavController, viewModel: CorpusViewModel, content: @Composable () -> Unit) {
    MuffassaScaffold(
        topBar = { CorpusViewTopAppBar(onNavigationIconClick = { navController.popBackStack() }) },
        bottomBar = { CorpusViewBottomAppBar() },
        fab = {},
        onClick = { viewModel.onEvent(CorpusEvent.EndEdit) },
    ) {
        content()
    }

}