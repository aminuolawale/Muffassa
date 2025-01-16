package com.aminuolawale.muffassa.presentation.home.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aminuolawale.muffassa.domain.model.Corpus
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.components.MuffassaScaffold
import com.aminuolawale.muffassa.presentation.home.HomeEvent
import com.aminuolawale.muffassa.presentation.home.HomeViewModel
import java.util.UUID

@Composable
fun HomeScaffold(
    viewModel: HomeViewModel,
    navController: NavController,
    content: @Composable () -> Unit,
) {
    if (viewModel.state.value.isCreating) {
        viewModel.state.value.userData?.userId?.let { creatorUserId ->
            CorpusCreationBottomSheet(
                corpus = Corpus(
                    id = UUID.randomUUID().toString(),
                    title = "",
                    creatorUserId = creatorUserId
                ),
                onSave = { viewModel.onEvent(HomeEvent.SaveCorpus(it)) },
                onDismiss = { viewModel.onEvent(HomeEvent.DiscardCorpus) })
        }
    }
    MuffassaScaffold(
        screen = Screen.Home,
        navController = navController,
        topBar = { HomeTopAppBar(viewModel, navController) },
        fab = { HomeFab(onClick = { viewModel.onEvent(HomeEvent.NewCorpus) }) }) {
        content()
    }
}