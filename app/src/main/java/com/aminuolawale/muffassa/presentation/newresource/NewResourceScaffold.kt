package com.aminuolawale.muffassa.presentation.newresource

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.components.MuffassaScaffold


@Composable
fun NewResourceScaffold(
    navController: NavController,
    viewModel: NewResourceViewModel,
    content: @Composable () -> Unit
) {
    MuffassaScaffold(
        topBar = {
            NewResourcesTopAppBar(
                onNavigationClick = { navController.popBackStack() },
                onSaveClick = { viewModel.onEvent(NewResourceEvent.Save) })
        },
        bottomBar = {},
        fab = {},
        onClick = {}) {
        content()
    }
}