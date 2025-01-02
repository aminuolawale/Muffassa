package com.aminuolawale.muffassa.presentation.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aminuolawale.muffassa.R

@Composable
fun SignInScreen(onSignInClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.mipmap.muffassa_background),
            contentScale = ContentScale.Crop,
            contentDescription = "background_image"
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Muffassa", fontSize = 36.sp, color = Color.White)
            Text(text = "Remember anything", fontSize = 14.sp, color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth(), onClick = onSignInClick) {
            Text(text = "Sign in with Google")
        }
    }
}