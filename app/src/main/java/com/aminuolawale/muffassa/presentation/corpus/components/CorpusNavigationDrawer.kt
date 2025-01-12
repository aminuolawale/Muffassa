package com.aminuolawale.muffassa.presentation.corpus.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewState


@Composable
fun CorpusNavigationDrawer(state: CorpusViewState) {
    rememberCoroutineScope()
    ModalNavigationDrawer(drawerState = state.drawerState, drawerContent = {
        ModalDrawerSheet {
        Spacer(modifier = Modifier.height(80.dp))
            Text("Muffassa", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
            HorizontalDivider()
            NavigationDrawerItem(
                label = { Text(text = state.corpus?.title?:"") },
                selected = false,
                onClick = { /*TODO*/ }
            )
            HorizontalDivider()
            NavigationDrawerItem(
                label = { Text("Quiz") },
                selected = false,
                onClick = { /*TODO*/ }
            )
            NavigationDrawerItem(
                label = { Text(text = "Snippets") },
                selected = false,
                onClick = { /*TODO*/ }
            )
            NavigationDrawerItem(
                label = { Text(text = "Resources") },
                selected = false,
                onClick = { /*TODO*/ }
            )
        }
    }) {

    }
}