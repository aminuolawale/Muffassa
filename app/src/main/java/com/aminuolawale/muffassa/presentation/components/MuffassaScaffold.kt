package com.aminuolawale.muffassa.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.Screen


@Composable
fun MuffassaScaffold(
    screen: Screen,
    navController: NavController,
    topBar: @Composable () -> Unit,
    fab: @Composable () -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        topBar = topBar,
        bottomBar = {
            MainAppBar(screen) {
                navController.navigate(it)
            }
        },
        floatingActionButton = fab
    ) { contentPadding ->
        content(Modifier.padding(contentPadding))
    }
}