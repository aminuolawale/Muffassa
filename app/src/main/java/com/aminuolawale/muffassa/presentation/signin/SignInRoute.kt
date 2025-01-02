package com.aminuolawale.muffassa.presentation.signin

import android.app.Activity.RESULT_OK
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun SignInRoute(
    context: Context,
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    googleAuthUiClient: GoogleAuthUiClient,
) {
    val signInViewModel = viewModel<SignInViewModel>()
    val signInState by signInViewModel.state.collectAsStateWithLifecycle()
    val activityLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
            if (it.resultCode == RESULT_OK) {
                it.data?.let {
                    lifecycleScope.launch {
                        val signInResult = googleAuthUiClient.signInWithIntent(it)
                        signInViewModel.onSignInResult(signInResult ?: return@launch)
                    }
                }
            }
        }
    LaunchedEffect(key1 = signInState.success) {
        if (signInState.success) {
            Toast.makeText(
                context,
                "Sign in successful",
                Toast.LENGTH_SHORT
            ).show()
            signInViewModel.resetState()
            navController.navigate("profile")

        }
    }
    SignInScreen {
        lifecycleScope.launch {
            googleAuthUiClient.getSignInIntentSender()?.let {
                activityLauncher.launch(IntentSenderRequest.Builder(it).build())
            }
        }
    }
}