package com.aminuolawale.muffassa.presentation.corpus.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.corpus.CorpusTab
import com.aminuolawale.muffassa.presentation.corpus.CorpusUri
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewState


@Composable
fun CorpusNavigationDrawer(
    navController: NavController,
    state: CorpusViewState
) {
    val corpusUri = CorpusUri(state.corpus?.id)
    ModalNavigationDrawer(drawerState = state.drawerState, drawerContent = {
        ModalDrawerSheet {
            Spacer(modifier = Modifier.height(80.dp))
            Text("Muffassa", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
            HorizontalDivider()
            NavigationDrawerItem(
                label = {
                    NavigationDRawerItemLabel(
                        text = { Text(text = state.corpus?.title ?: "") },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Corpus Home") })
                },
                selected = state.activeTab == CorpusTab.HOME,
                onClick = {
                    navController.navigate(corpusUri.home)
                }
            )
            HorizontalDivider()
            NavigationDrawerItem(
                label = {
                    NavigationDRawerItemLabel(
                        text = { Text(text = "Quiz") },
                        icon = { Icon(Icons.Outlined.Create, contentDescription = "Corpus Quiz") })
                },
                selected = state.activeTab == CorpusTab.QUIZ,
                onClick = { navController.navigate(corpusUri.quiz) }
            )
            NavigationDrawerItem(
                label = {
                    NavigationDRawerItemLabel(
                        text = { Text(text = "Snippets") },
                        icon = {
                            Icon(
                                Icons.Outlined.MailOutline,
                                contentDescription = "Corpus Snippets"
                            )
                        })
                },
                selected = state.activeTab == CorpusTab.SNIPPETS,
                onClick = { navController.navigate(corpusUri.snippets) }
            )
            NavigationDrawerItem(
                label = {
                    NavigationDRawerItemLabel(
                        text = { Text(text = "Resources") },
                        icon = {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Corpus Resources"
                            )
                        })
                },
                selected = state.activeTab == CorpusTab.RESOURCES,
                onClick = {
                    navController.navigate(corpusUri.resources)
                }
            )
            HorizontalDivider()
            NavigationDrawerItem(
                label = {
                    NavigationDRawerItemLabel(
                        text = { Text(text = "Settings") },
                        icon = {
                            Icon(
                                Icons.Default.Settings,
                                contentDescription = "Corpus Resources"
                            )
                        })
                },
                selected = state.activeTab == CorpusTab.SETTINGS,
                onClick = {
                    navController.navigate(corpusUri.settings)
                }
            )
        }
    }) {

    }
}

@Composable
fun NavigationDRawerItemLabel(text: @Composable () -> Unit, icon: @Composable () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        icon()
        Spacer(modifier = Modifier.width(10.dp))
        text()
    }
}