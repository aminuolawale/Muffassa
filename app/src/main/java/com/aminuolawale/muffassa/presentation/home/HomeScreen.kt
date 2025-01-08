package com.aminuolawale.muffassa.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.components.MuffassaScaffold
import com.aminuolawale.muffassa.presentation.home.components.CorpusGrid
import com.aminuolawale.muffassa.presentation.signin.UserData

@Composable
fun HomeScreen(navController: NavController, userData: UserData?, homeViewModel: HomeViewModel) {
    homeViewModel.state.collectAsState().let {
        MuffassaScaffold(
            screen = Screen.Home,
            profilePictureUrl = userData?.profilePictureUrl,
            showMenuAppBar = it.value.isSelecting,
            onFabClick = { homeViewModel.onEvent(HomeEvent.NewCorpus) },
            onHomeClick = { navController.navigate(Screen.Home.route) },
            onProfileClick = { navController.navigate(Screen.Profile.route) },
            onMenuAppBarCancelClick = {homeViewModel.onEvent(HomeEvent.EndSelection)},
            onMenuAppBarDeleteClick = {homeViewModel.onEvent(HomeEvent.DeleteCorpora)}) {
            CorpusGrid(homeViewModel)
        }
    }
}





