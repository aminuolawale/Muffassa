package com.aminuolawale.muffassa.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MuffassaScaffold(
    topBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    // all screens should have fab
    fab: @Composable () -> Unit,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier
            .clickable { onClick() },
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = fab
    ) {
        content()
    }
}