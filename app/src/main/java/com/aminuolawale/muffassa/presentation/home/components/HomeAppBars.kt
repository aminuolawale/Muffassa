package com.aminuolawale.muffassa.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.home.HomeEvent
import com.aminuolawale.muffassa.presentation.home.HomeViewModel
import com.aminuolawale.muffassa.presentation.home.HomeViewState


@Composable
fun HomeTopAppBar(
    viewModel: HomeViewModel,
    navController: NavController,
) {
    viewModel.state.collectAsState().let { state ->
        if (!state.value.isSelecting) {
            DefaultAppBar(
                homeViewState = state.value,
                onProfileClick = { navController.navigate(Screen.Profile.route) })
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(
    homeViewState: HomeViewState,
    onProfileClick: () -> Unit
) {
    TopAppBar(
        title = { Text("Corpora") },
        actions = {
            Spacer(modifier = Modifier.width(10.dp))
            AsyncImage(
                model = homeViewState.userData?.profilePictureUrl,
                contentDescription = "profile image",
                modifier = Modifier
                    .size(32.dp)
                    .clip(
                        CircleShape
                    )
                    .clickable { onProfileClick() },
                contentScale = ContentScale.Crop
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionAppBar(onDeleteClick: () -> Unit, onCancelClick: () -> Unit) {
    TopAppBar(title = {}, actions = {
        IconButton(onClick = { onDeleteClick() }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }, navigationIcon = {
        IconButton(onClick = { onCancelClick() }) {
            Icon(
                Icons.Default.Close,
                contentDescription = "Cancel"
            )
        }
    })
}