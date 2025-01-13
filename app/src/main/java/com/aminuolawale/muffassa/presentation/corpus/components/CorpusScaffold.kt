package com.aminuolawale.muffassa.presentation.corpus.components

import android.net.Uri
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
        CorpusNavigationDrawer(navController, state.value) {

            MuffassaScaffold(
                screen = Screen.CorpusHome,
                navController = navController,
                topBar = {
                    CorpusViewTopAppBar(
                        state = state.value,
                        onNavigationIconClick = { viewModel.onEvent(CorpusEvent.ToggleNavDrawer) },
                        onOptionsClick = { viewModel.onEvent(CorpusEvent.ToggleOptionsMenu) },
                        onEditClick = {
                            viewModel.onEvent(CorpusEvent.BeginEdit)
                            viewModel.onEvent(CorpusEvent.OptionsMenu(false))
                        },
                        onOptionsDismissRequest = { viewModel.onEvent(CorpusEvent.OptionsMenu(false)) })
                },
                fab = {
                    when (state.value.activeTab) {
                        CorpusTab.RESOURCES -> {
                            val uri = Uri.Builder().path(Screen.NewResource.route)
                                .appendQueryParameter("corpusId", state.value.corpus?.id).build()
                            ResourcesFab(onClick = { navController.navigate(uri.toString()) })
                        }

                        else -> {}
                    }
                }
            ) {
                content()

            }
        }
    }

}