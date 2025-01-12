package com.aminuolawale.muffassa.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.home.HomeViewModel


@Composable
fun MuffassaScaffold(
    screen: Screen,
    navController: NavController,
    topBar: @Composable () -> Unit,
    fab: @Composable () -> Unit,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    Scaffold(
        modifier = Modifier
            .clickable { onClick() },
        topBar = topBar,
        bottomBar = {
            MainAppBar(screen, homeViewModel.state.value.userData?.profilePictureUrl) {
                navController.navigate(it)
            }
        },
        floatingActionButton = fab
    ) {
        content()
    }
}