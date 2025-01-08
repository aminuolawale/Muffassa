package com.aminuolawale.muffassa.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.aminuolawale.muffassa.presentation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MuffassaScaffold(
    screen: Screen,
    profilePictureUrl: String? = null,
    showMenuAppBar: Boolean = false,
    showSearchBar: Boolean = false,
    onClick: () -> Unit = {},
    onFabClick: () -> Unit = {},
    onNavigationIconClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onMenuAppBarCancelClick: () -> Unit = {},
    onMenuAppBarDeleteClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    rememberModalBottomSheetState(confirmValueChange = { false })

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .clickable { onClick() },
        topBar = {
            when (screen) {
                Screen.Corpus -> CorpusViewTopAppBar(onNavigationIconClick)
                Screen.Home -> {
                    if (showMenuAppBar) {
                        TopAppBar(title = {}, actions = {
                            IconButton(onClick = { onMenuAppBarDeleteClick() }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                            }
                        }, navigationIcon = {
                            IconButton(onClick = { onMenuAppBarCancelClick() }) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Cancel"
                                )
                            }
                        })
                    }

                    else {
                        HomeTopAppBar(scrollBehavior, profilePictureUrl, onProfileClick, onSearchClick)

                    }
                }

                Screen.Profile -> HomeTopAppBar( scrollBehavior)
                Screen.SignIn -> {}
            }
        },
        floatingActionButton = {
            when (screen) {
                Screen.Corpus -> HomeFAB(onFabClick)
                Screen.Home -> HomeFAB(onFabClick)
                Screen.Profile -> {}
                Screen.SignIn -> {}
            }
        },
    ) {
        content()
    }
}


