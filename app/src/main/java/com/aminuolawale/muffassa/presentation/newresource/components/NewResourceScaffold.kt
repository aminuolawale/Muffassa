package com.aminuolawale.muffassa.presentation.newresource.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
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
        topBar = {
            NewResourcesTopAppBar(
                onNavigationClick = { navController.popBackStack() })
        },
        bottomBar = {},
        fab = {
            NewResourceFab(onClick = {
                viewModel.onEvent(NewResourceEvent.Save)
            })
        },
        onClick = {}) {
        content()
    }
}