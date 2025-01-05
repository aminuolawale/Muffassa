package com.aminuolawale.muffassa.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.signin.UserData
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeRoute(navController: NavController, userData: UserData?, homeViewModel: HomeViewModel) {
    LaunchedEffect(key1 = true) {
        homeViewModel.viewEffect.collectLatest {
            when(it) {
                HomeViewEffect.NewCorpus -> navController.navigate("corpus")
            }
        }
    }
    HomeScreen(userData, homeViewModel) {
        navController.navigate("profile")
    }
}