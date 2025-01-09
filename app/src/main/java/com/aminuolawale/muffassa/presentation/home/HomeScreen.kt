package com.aminuolawale.muffassa.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.home.components.CorpusGrid
import com.aminuolawale.muffassa.presentation.home.components.HomeScaffold
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel
            .viewEffect.collectLatest {
                when (it) {
                    is HomeViewEffect.ViewCorpus -> {
                        navController.navigate("corpus?corpusId=${it.corpusId}")
                        viewModel.onViewEffectComplete()
                    }

                    HomeViewEffect.NoViewEffect -> {}
                }
            }
    }
    HomeScaffold(viewModel = viewModel, navController = navController){
        CorpusGrid(viewModel)

    }
}





