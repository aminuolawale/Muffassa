package com.aminuolawale.muffassa.presentation.signin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()


    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                success = result.userData != null,
                errorString = result.errorString
            )
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}