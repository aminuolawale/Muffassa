package com.aminuolawale.muffassa.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    profilePictureUrl: String? = null,
    onProfileClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
) {
    TopAppBar(
        title = { Text("Corpora") },
        actions = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
            Spacer(modifier = Modifier.width(10.dp))
            AsyncImage(
                model = profilePictureUrl,
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
        scrollBehavior = scrollBehavior
    )
}






