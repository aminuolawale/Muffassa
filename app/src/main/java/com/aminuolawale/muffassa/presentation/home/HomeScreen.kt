package com.aminuolawale.muffassa.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.aminuolawale.muffassa.presentation.signin.UserData

@Composable
fun HomeScreen(userData: UserData?, onProfileImageClick: () -> Unit) {
    Box {
        // Appbar
        Surface(color = Color.Black, modifier = Modifier.fillMaxWidth()) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.TopCenter)
            ) {
                Text("Muffassa", color = Color.White, fontSize = 24.sp)
            }
        }

        // Grid
        Surface(
            shape = RoundedCornerShape(corner = CornerSize(0.dp)),
            border = BorderStroke(1.dp, color = Color.Black),
            modifier = Modifier
                .height(36.dp)
                .align(Alignment.Center), onClick = {}
        ) {
            Text("new")

        }

        // Bottombar
        Surface(
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(60.dp)
                .shadow(elevation = 5.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp)
            ) {
                Icon(Icons.Outlined.Home, contentDescription = "Home", tint = Color.White)
                Surface(shape = CircleShape, onClick = onProfileImageClick) {
                    AsyncImage(
                        model = userData?.profilePictureUrl,
                        contentDescription = "profile image",
                        modifier = Modifier
                            .size(32.dp)
                            .clip(
                                CircleShape
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

    }
}





