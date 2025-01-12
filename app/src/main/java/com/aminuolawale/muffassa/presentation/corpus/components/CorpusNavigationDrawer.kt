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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aminuolawale.muffassa.domain.model.Corpus
import com.aminuolawale.muffassa.presentation.corpus.CorpusTab
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewState


@Composable
fun CorpusNavigationDrawer(
    state: CorpusViewState,
    onHomeClick: () -> Unit,
    onResourcesClick: () -> Unit
) {
    ModalNavigationDrawer(drawerState = state.drawerState, drawerContent = {
        ModalDrawerSheet {
            Spacer(modifier = Modifier.height(80.dp))
            Text("Muffassa", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
            HorizontalDivider()
            NavigationDrawerItem(
                label = { Text(text = state.corpus?.title ?: "") },
                selected = state.activeTab == CorpusTab.HOME,
                onClick = onHomeClick
            )
            HorizontalDivider()
            NavigationDrawerItem(
                label = { Text("Quiz") },
                selected = state.activeTab == CorpusTab.QUIZ,
                onClick = { /*TODO*/ }
            )
            NavigationDrawerItem(
                label = { Text(text = "Snippets") },
                selected = state.activeTab == CorpusTab.SNIPPETS,
                onClick = { /*TODO*/ }
            )
            NavigationDrawerItem(
                label = { Text(text = "Resources") },
                selected = state.activeTab == CorpusTab.RESOURCES,
                onClick = onResourcesClick
            )
        }
    }) {

    }
}