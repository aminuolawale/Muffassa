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
import androidx.compose.material.icons.outlined.Delete
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


sealed class BottomBarState {
    data object Default : BottomBarState()
    data object Selection : BottomBarState()
}

@Composable
fun ContentWithBottomBar(
    navController: NavController,
    userData: UserData?,
    onClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    bottomBarState: BottomBarState = BottomBarState.Default,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().clickable { onClick.invoke() }, ) {
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
                if (bottomBarState is BottomBarState.Default) {
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
                } else {
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = "Delete",
                        tint = Color.White,
                        modifier = Modifier.clickable { onDeleteClick.invoke() })
                }
            }
        }
    }
}