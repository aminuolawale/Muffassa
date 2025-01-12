package com.aminuolawale.muffassa.presentation.corpus.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.components.MuffassaScaffold
import com.aminuolawale.muffassa.presentation.corpus.CorpusEvent
import com.aminuolawale.muffassa.presentation.corpus.CorpusTab
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewModel
import com.aminuolawale.muffassa.presentation.newresource.components.ResourcesFab


@Composable
fun CorpusScaffold(
    navController: NavController,
    viewModel: CorpusViewModel,
    content: @Composable () -> Unit
) {
    viewModel.state.collectAsState().let { state ->
        MuffassaScaffold(
            screen = Screen.CorpusHome,
            navController = navController,
            topBar = {
                CorpusViewTopAppBar(
                    state = state.value,
                    onNavigationIconClick = { viewModel.onEvent(CorpusEvent.OpenNavDrawer) })
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
            CorpusNavigationDrawer(state.value)

        }
    }

}