package com.aminuolawale.muffassa.presentation.corpus.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.components.MuffassaScaffold
import com.aminuolawale.muffassa.presentation.corpus.CorpusEvent
import com.aminuolawale.muffassa.presentation.corpus.CorpusTab
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewModel
import com.aminuolawale.muffassa.presentation.home.components.ResourcesFab


@Composable
fun CorpusScaffold(
    navController: NavController,
    viewModel: CorpusViewModel,
    content: @Composable () -> Unit
) {
    viewModel.state.collectAsState().let { state ->
        MuffassaScaffold(
            topBar = {
                CorpusViewTopAppBar(
                    state = state.value,
                    onNavigationIconClick = { navController.popBackStack() })
            },
            bottomBar = {
                CorpusViewBottomAppBar(
                    activeTab = state.value.activeTab,
                    onButtonClick = { viewModel.onEvent(CorpusEvent.SelectTab(it)) })
            },
            fab = {
                when (state.value.activeTab) {
                    CorpusTab.RESOURCES -> ResourcesFab(onClick = { navController.navigate(Screen.NewResource.route + "?corpusId=${state.value.corpus?.id}") })
                    else -> {}
                }
            },
            onClick = { viewModel.onEvent(CorpusEvent.EndEdit) },
        ) {
            content()
        }
    }

}