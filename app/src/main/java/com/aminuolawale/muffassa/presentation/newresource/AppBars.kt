package com.aminuolawale.muffassa.presentation.newresource

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewResourcesTopAppBar(onNavigationClick: () -> Unit, onSaveClick: () -> Unit) {
    TopAppBar(title = { Text("New Resource") }, navigationIcon = {
        IconButton(onClick = { onNavigationClick() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
    }, actions = {
        IconButton(onClick = { onSaveClick() }) {
            Icon(Icons.Outlined.Check, contentDescription = "Save")
        }
    })
}