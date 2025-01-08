package com.aminuolawale.muffassa

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.corpus.CorpusRoute
import com.aminuolawale.muffassa.presentation.corpus.CorpusViewModel
import com.aminuolawale.muffassa.presentation.home.HomeEvent
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
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(applicationContext)
    }
    private val homeViewModel: HomeViewModel by viewModels()
    private val corpusViewModel: CorpusViewModel by viewModels()
    private var callback: OnBackPressedCallback? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(applicationContext)
        setContent {
            MuffassaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    homeViewModel.state.collectAsState().let {
                        if (it.value.isSelecting) {
                            callback = this.onBackPressedDispatcher.addCallback {
                                homeViewModel.onEvent(HomeEvent.EndSelection)
                            }
                        } else {
                            callback?.remove()
                        }
                    }
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "sign_in") {
                        composable(route = Screen.SignIn.route) {
                            SignInRoute(
                                applicationContext,
                                navController,
                                lifecycleScope,
                                googleAuthUiClient
                            )
                        }
                        composable(route = Screen.Profile.route) {
                            ProfileRoute(
                                applicationContext,
                                navController,
                                lifecycleScope,
                                googleAuthUiClient
                            )
                        }
                        composable(route = Screen.Home.route) {
                            HomeRoute(
                                navController,
                                googleAuthUiClient.getSignedInUser(),
                                homeViewModel
                            )
                        }
                        composable(
                            route = "${Screen.Corpus.route}?corpusId={corpusId}",
                            arguments = listOf(navArgument(name = "corpusId") {
                                type = NavType.StringType
                                defaultValue = ""

                            })
                        ) {

                            CorpusRoute(
                                navController,
                                corpusViewModel,
                                it.arguments?.getString("corpusId")
                            )
                        }
                    }
                }
            }
        }
    }
}