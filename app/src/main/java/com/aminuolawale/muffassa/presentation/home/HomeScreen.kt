package com.aminuolawale.muffassa.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.components.ContentWithBottomBar
import com.aminuolawale.muffassa.presentation.home.components.CorpusGrid
import com.aminuolawale.muffassa.presentation.signin.UserData

@Composable
fun HomeScreen(navController: NavController, userData: UserData?, homeViewModel: HomeViewModel) {
    ContentWithBottomBar(navController , userData) {
        CorpusGrid(homeViewModel)
    }
}





