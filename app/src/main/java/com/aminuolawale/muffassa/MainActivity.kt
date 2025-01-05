package com.aminuolawale.muffassa

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aminuolawale.muffassa.presentation.corpus.CorpusRoute
import com.aminuolawale.muffassa.presentation.home.HomeRoute
import com.aminuolawale.muffassa.presentation.home.HomeViewModel
import com.aminuolawale.muffassa.presentation.profile.ProfileRoute
import com.aminuolawale.muffassa.presentation.signin.GoogleAuthUiClient
import com.aminuolawale.muffassa.presentation.signin.SignInRoute
import com.aminuolawale.muffassa.ui.theme.MuffassaTheme
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val  googleAuthUiClient by lazy {
        GoogleAuthUiClient(applicationContext)
    }
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(applicationContext)
        setContent {
            MuffassaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "sign_in") {
                        composable(route = "sign_in") {
                            SignInRoute(applicationContext,navController, lifecycleScope, googleAuthUiClient)
                        }
                        composable(route = "profile") {
                            ProfileRoute(applicationContext, navController, lifecycleScope ,googleAuthUiClient)
                        }
                        composable(route = "home") {
                            HomeRoute(
                                navController,
                                googleAuthUiClient.getSignedInUser(),
                                homeViewModel
                            )
                        }
                        composable(route = "corpus") {
                            CorpusRoute()
                        }
                    }
                }
            }
        }
    }
}