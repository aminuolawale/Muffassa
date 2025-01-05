package com.aminuolawale.muffassa.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.aminuolawale.muffassa.presentation.signin.UserData


@Composable
fun ContentWithBottomBar(
    navController: NavController,
    userData: UserData?,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        content()
        // Bottombar
        Surface(
            color = Color.Black,
            modifier = Modifier
                .height(60.dp)
                .fillMaxSize()
                .align(Alignment.BottomCenter)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(20.dp, 0.dp, 20.dp, 0.dp)
                    .fillMaxSize()
            ) {
                Icon(
                    Icons.Outlined.Home,
                    contentDescription = "Home",
                    tint = Color.White,
                    modifier = Modifier.clickable { navController.navigate("home") })
                AsyncImage(
                    model = userData?.profilePictureUrl,
                    contentDescription = "profile image",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(
                            CircleShape
                        )
                        .clickable { navController.navigate("profile") },
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}