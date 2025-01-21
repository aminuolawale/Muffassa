package com.aminuolawale.muffassa.presentation.corpus.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aminuolawale.muffassa.presentation.corpus.CorpusTab
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CorpusViewTopAppBar(
    state: CorpusViewState,
    onNavigationIconClick: () -> Unit,
    onOptionsClick: () -> Unit,
    onEditClick: () -> Unit,
    onOptionsDismissRequest: () -> Unit
) {
    TopAppBar(
        title = {
            when (state.activeTab) {
                CorpusTab.HOME -> {}
                else -> {
                    Text(state.corpus?.title ?: "")
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Corpus Menu"
                )
            }
        },
        actions = {
            IconButton(onClick = { onOptionsClick() }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Corpus options")
            }
            if (state.optionsMenu) {
                OptionsMenu(
                    state,
                    onDeleteClick =onEditClick ,
                    onDismissRequest = onOptionsDismissRequest
                )
            }
        },
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    )
}

@Composable
fun BottomBarTabButton(
    isActive: Boolean,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(80.dp)
            .clickable { onClick() },
        color = if (isActive) Color.LightGray else Color.Transparent
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}