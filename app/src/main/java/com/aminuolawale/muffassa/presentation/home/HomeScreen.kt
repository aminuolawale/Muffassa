package com.aminuolawale.muffassa.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.aminuolawale.muffassa.presentation.signin.UserData

@Composable
fun HomeScreen(userData: UserData?, onProfileImageClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            // Appbar
            Surface(color = Color.Black, modifier = Modifier.fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text("Muffassa", color = Color.White, fontSize = 24.sp)
                }
            }

            // Grid
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                item {
                    Box(modifier = Modifier.padding(5.dp)) {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .size(100.dp),
                            border = BorderStroke(1.dp, color = Color.Black),
                            onClick = {},
                        ) {
                        }
                        Text("new", modifier = Modifier.align(Alignment.Center))

                    }

                }
            }
        }

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





