package com.aminuolawale.muffassa.presentation.home

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.corpus.CorpusUri
import com.aminuolawale.muffassa.presentation.home.components.HomeScaffold
import com.aminuolawale.muffassa.presentation.home.components.CorpusList
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel
            .viewEffect.collectLatest {
                when (it) {
                    is HomeViewEffect.ViewCorpus -> {
                        navController.navigate(CorpusUri(it.corpusId).home)
                    }

                    HomeViewEffect.NoViewEffect -> {}
                }
            }
    }
    HomeScaffold(viewModel = viewModel, navController = navController) {
        CorpusList(viewModel, it)
    }
}





