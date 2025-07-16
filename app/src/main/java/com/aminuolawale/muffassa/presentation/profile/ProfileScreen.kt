package com.aminuolawale.muffassa.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.components.MainBottomAppBar
import com.aminuolawale.muffassa.presentation.components.MuffassaScaffold
import com.aminuolawale.muffassa.presentation.home.components.DefaultAppBar
import com.aminuolawale.muffassa.presentation.signin.UserData

@Composable
fun ProfileScreen(navController: NavController, userData: UserData?, onSignOutClick: () -> Unit) {
    MuffassaScaffold(
        screen = Screen.Profile,
        navController = navController,
        topBar = {
            DefaultAppBar(
                title = { Text("Profile") },
                profilePictureUrl = userData?.profilePictureUrl,
                onProfileClick = { navController.navigate(Screen.Profile.route) })
        },
        bottomBar = { MainBottomAppBar(screen = Screen.Home) { navController.navigate(it) } },
        fab = {}) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            userData?.profilePictureUrl?.let {
                AsyncImage(
                    model = it,
                    contentDescription = "profile image",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(
                            CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            userData?.username?.let {
                Text(text = it)
            }

            Button(onClick = onSignOutClick) {
                Text(text = "Sign out")
            }
        }
    }
}