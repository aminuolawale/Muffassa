package com.aminuolawale.muffassa.presentation.newresource.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.components.MainAppBar
import com.aminuolawale.muffassa.presentation.components.MuffassaScaffold
import com.aminuolawale.muffassa.presentation.newresource.NewResourceEvent
import com.aminuolawale.muffassa.presentation.newresource.NewResourceViewModel


@Composable
fun NewResourceScaffold(
    navController: NavController,
    viewModel: NewResourceViewModel,
    content: @Composable () -> Unit
) {
    MuffassaScaffold(
        screen = Screen.NewResource,
        navController = navController,
        topBar = {
            NewResourcesTopAppBar(
                onNavigationClick = { navController.popBackStack() })
        },
        bottomBar = { MainAppBar(screen = Screen.Home) { navController.navigate(it) } },
        fab = {
            NewResourceFab(onClick = {
                viewModel.onEvent(NewResourceEvent.Save)
            })
        }) {
        content()
    }
}