package com.aminuolawale.muffassa.presentation.signin

data class SignInResult(val userData: UserData?, val errorString: String?)


data class UserData(val userId: String, val username: String?, val profilePictureUrl: String?)
