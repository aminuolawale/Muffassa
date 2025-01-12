package com.aminuolawale.muffassa.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.corpus.components.BottomBarTabButton

@Composable
fun MainAppBar(
    screen: Screen,
    profilePictureUrl: String?,
    onButtonClick: (String) -> Unit,
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
                isActive = screen == Screen.Home,
                onClick = { onButtonClick(Screen.Home.route) }) {
                Icon(Icons.Default.Home, contentDescription = "Home")

            }
            AsyncImage(
                model = profilePictureUrl,
                contentDescription = "profile image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(
                        CircleShape
                    )
                    .clickable { },
                contentScale = ContentScale.Crop
            )
        }
    }
}