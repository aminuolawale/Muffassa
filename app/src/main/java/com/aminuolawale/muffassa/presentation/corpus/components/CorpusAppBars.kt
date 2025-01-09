package com.aminuolawale.muffassa.presentation.corpus.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.unit.sp
import com.aminuolawale.muffassa.presentation.corpus.CorpusTab


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
fun CorpusViewBottomAppBar(
    activeTab: CorpusTab = CorpusTab.HOME,
    onButtonClick: (CorpusTab) -> Unit,
) {
    BottomAppBar {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BottomBarTabButton(
                text = "Home",
                isActive = activeTab == CorpusTab.HOME,
                onClick = { onButtonClick(CorpusTab.HOME) }) {
                Icon(Icons.Default.Home, contentDescription = "Home")
            }
            BottomBarTabButton(
                text = "Quiz",
                isActive = activeTab == CorpusTab.QUIZ,
                onClick = { onButtonClick(CorpusTab.QUIZ) }
            ) {
                Icon(Icons.Default.Info, contentDescription = "Quiz")

            }
            BottomBarTabButton(
                text = "Snippets",
                isActive = activeTab == CorpusTab.SNIPPETS,
                onClick = { onButtonClick(CorpusTab.SNIPPETS) }
            ) {
                Icon(Icons.Default.Star, contentDescription = "Snippets")
            }
            BottomBarTabButton(
                text = "Resources",
                isActive = activeTab == CorpusTab.RESOURCES,
                onClick = { onButtonClick(CorpusTab.RESOURCES) }
            ) {
                Icon(Icons.Default.Menu, contentDescription = "Resources")
            }

        }
    }
}

@Composable
fun BottomBarTabButton(
    text: String?,
    isActive: Boolean,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(80.dp)
            .clickable { onClick() },
        color = if (isActive) Color.Red else Color.Transparent
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
            text?.let {
                Text(text, fontSize = 14.sp)
            }
        }
    }
}