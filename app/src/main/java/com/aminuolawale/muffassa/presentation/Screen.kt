package com.aminuolawale.muffassa.presentation

sealed class Screen(val route: String) {
    data object SignIn : Screen(route = "sign_in")
    data object Profile : Screen(route = "profile")
    data object Home : Screen(route = "home")
    data object Corpus : Screen(route = "corpus")
    data object EditCorpus: Screen(route = "editcorpus")
    data object NewResource: Screen(route = "new_resource")
}
