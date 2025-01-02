package com.aminuolawale.muffassa.presentation.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aminuolawale.muffassa.presentation.signin.GoogleAuthUiClient
import com.aminuolawale.muffassa.presentation.signin.SignInViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileRoute(
    context: Context,
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    googleAuthUiClient: GoogleAuthUiClient,
) {
    ProfileScreen(userData = googleAuthUiClient.getSignedInUser()) {
        lifecycleScope.launch {
            googleAuthUiClient.signOut()
            Toast.makeText(context, "Signed out", Toast.LENGTH_SHORT).show()
            navController.navigate("sign_in")
        }
    }
}