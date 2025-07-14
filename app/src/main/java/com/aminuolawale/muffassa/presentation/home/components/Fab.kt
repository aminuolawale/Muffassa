package com.aminuolawale.muffassa.presentation.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.aminuolawale.muffassa.presentation.home.HomeViewState

@Composable
fun HomeFab(state: HomeViewState, onClick: () -> Unit) {
    if (!state.isSelecting) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Outlined.Add, contentDescription = "Add")
    }

    }
}