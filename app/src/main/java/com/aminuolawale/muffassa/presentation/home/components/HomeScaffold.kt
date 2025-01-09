package com.aminuolawale.muffassa.presentation.home.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.components.MuffassaScaffold
import com.aminuolawale.muffassa.presentation.home.HomeEvent
import com.aminuolawale.muffassa.presentation.home.HomeViewModel

@Composable
fun HomeScaffold(
    viewModel: HomeViewModel,
    navController: NavController,
    content: @Composable () -> Unit,
) {
    MuffassaScaffold(
        topBar = { HomeTopAppBar(viewModel, navController) },
        bottomBar = {},
        fab = { HomeFab(onClick = { viewModel.onEvent(HomeEvent.NewCorpus) }) },
        onClick = {}) {
        content()
    }
}