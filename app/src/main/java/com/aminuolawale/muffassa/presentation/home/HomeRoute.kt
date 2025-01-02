package com.aminuolawale.muffassa.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.signin.UserData

@Composable
fun HomeRoute(navController: NavController, userData: UserData?) {
    HomeScreen(userData) {
        navController.navigate("profile")
    }
}