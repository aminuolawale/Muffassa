package com.aminuolawale.muffassa.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.components.BottomBarState
import com.aminuolawale.muffassa.presentation.components.ContentWithBottomBar
import com.aminuolawale.muffassa.presentation.home.components.CorpusGrid
import com.aminuolawale.muffassa.presentation.signin.UserData

@Composable
fun HomeScreen(navController: NavController, userData: UserData?, homeViewModel: HomeViewModel) {
    homeViewModel.state.collectAsState().let {
        ContentWithBottomBar(
            navController,
            userData,
            { homeViewModel.onEvent(HomeEvent.DeleteCorpora) },
            if (it.value.isSelecting) BottomBarState.Selection else BottomBarState.Default,
        ) {
            CorpusGrid(homeViewModel)
        }
    }
}





