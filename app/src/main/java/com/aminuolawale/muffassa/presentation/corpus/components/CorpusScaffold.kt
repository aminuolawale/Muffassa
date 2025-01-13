package com.aminuolawale.muffassa.presentation.corpus.components

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.components.MuffassaScaffold
import com.aminuolawale.muffassa.presentation.corpus.CorpusEvent
import com.aminuolawale.muffassa.presentation.corpus.CorpusTab
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewEffect
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewModel
import com.aminuolawale.muffassa.presentation.newresource.components.ResourcesFab
import kotlinx.coroutines.flow.collectLatest


@Composable
fun CorpusScaffold(
    navController: NavController,
    viewModel: CorpusViewModel,
    content: @Composable () -> Unit
) {
    LaunchedEffect(key1 = viewModel.state.value.activeTab) {
        viewModel.viewEffect.collectLatest {
            when (it) {
                CorpusViewEffect.NoViewEffect -> {}
                is CorpusViewEffect.SelectTab -> {
                    val uriBuilder =
                        Uri.Builder().path(Screen.CorpusHome.route).appendPath(viewModel.state.value.corpus?.id)
                    when (it.tab) {
                        CorpusTab.HOME -> uriBuilder.appendPath("home")
                        CorpusTab.QUIZ -> uriBuilder.appendPath("quiz")
                        CorpusTab.SNIPPETS -> uriBuilder.appendPath("snippets")
                        CorpusTab.RESOURCES -> uriBuilder.appendPath("resources")
                    }
                    viewModel.onEvent(CorpusEvent.NavDrawer)
                    navController.navigate(uriBuilder.build().toString())
                }
            }
        }
    }
    viewModel.state.collectAsState().let { state ->
        MuffassaScaffold(
            screen = Screen.CorpusHome,
            navController = navController,
            topBar = {
                CorpusViewTopAppBar(
                    state = state.value,
                    onNavigationIconClick = { viewModel.onEvent(CorpusEvent.NavDrawer) })
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
            CorpusNavigationDrawer(state.value, onHomeClick = {
                viewModel.onEvent(CorpusEvent.SelectTab(CorpusTab.HOME))
            }, onResourcesClick = {
                viewModel.onEvent(CorpusEvent.SelectTab(CorpusTab.RESOURCES))
            })

        }
    }

}