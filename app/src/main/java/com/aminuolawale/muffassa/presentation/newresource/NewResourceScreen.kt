package com.aminuolawale.muffassa.presentation.newresource

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import com.aminuolawale.muffassa.presentation.components.MuffassaScaffold

@Composable
fun NewResourceScreen() {
    MuffassaScaffold(
        topBar = {},
        bottomBar = {},
        fab = {},
        onClick = {},
    ) {

        Column {
            TextField(value = "", onValueChange = {})
            TextField(value = "", onValueChange = {})
        }
    }
}