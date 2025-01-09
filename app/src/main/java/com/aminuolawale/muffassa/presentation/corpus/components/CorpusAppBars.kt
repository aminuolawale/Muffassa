package com.aminuolawale.muffassa.presentation.corpus.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aminuolawale.muffassa.presentation.components.BottomBarTabButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CorpusViewTopAppBar(onNavigationIconClick: () -> Unit) {
    TopAppBar(
        modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp),
        title = { },
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    )
}

@Composable
fun CorpusViewBottomAppBar() {
    BottomAppBar {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BottomBarTabButton(text= "Quiz", isActive = false) {
                Icon(Icons.Default.Info, contentDescription = "Quiz")

            }
            BottomBarTabButton(text = "Snippets", isActive = false) {
                Icon(Icons.Default.Star, contentDescription = "Snippets")
            }
            BottomBarTabButton(text="Resources", isActive = false) {
                Icon(Icons.Default.Menu, contentDescription = "Resources")
            }
        }
    }
}