package com.aminuolawale.muffassa.presentation.newresource

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.components.MuffassaScaffold

@Composable
fun NewResourceRoute(navController: NavController) {
    MuffassaScaffold(
        screen = Screen.NewResource,
        onNavigationIconClick = { navController.popBackStack() },
    ) {

        Column {
            TextField(value = "", onValueChange = {})
            TextField(value = "", onValueChange = {})
        }
    }
}