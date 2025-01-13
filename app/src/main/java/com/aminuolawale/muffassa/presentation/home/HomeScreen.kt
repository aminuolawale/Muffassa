package com.aminuolawale.muffassa.presentation.home

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.home.components.CorpusList
import com.aminuolawale.muffassa.presentation.home.components.HomeScaffold
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel
            .viewEffect.collectLatest {
                when (it) {
                    is HomeViewEffect.ViewCorpus -> {
                        val uri =
                            Uri.Builder().path(Screen.CorpusHome.route).appendPath(it.corpusId)
                                .appendPath("home")
                                .build()
                        navController.navigate(uri.toString())
                    }

                    HomeViewEffect.NoViewEffect -> {}
                }
            }
    }
    HomeScaffold(viewModel = viewModel, navController = navController) {
        CorpusList(viewModel)

    }
}





