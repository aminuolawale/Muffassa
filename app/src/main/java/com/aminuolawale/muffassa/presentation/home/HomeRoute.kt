package com.aminuolawale.muffassa.presentation.home

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.signin.UserData

@Composable
fun HomeRoute(context: Context, navController: NavController, userData: UserData?, homeViewModel: HomeViewModel) {
    HomeScreen(userData, homeViewModel) {
        navController.navigate("profile")
    }
}