package com.aminuolawale.muffassa.presentation.newresource

import androidx.compose.runtime.Composable
import com.aminuolawale.muffassa.presentation.components.MuffassaScaffold


@Composable
fun NewResourceScaffold(content: @Composable () -> Unit) {
    MuffassaScaffold(topBar = {}, bottomBar = {}, fab = {}, onClick = {}) {
        content()
    }
}