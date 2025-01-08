package com.aminuolawale.muffassa.presentation.corpus

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminuolawale.muffassa.domain.repository.CorpusRepository
import com.aminuolawale.muffassa.presentation.signin.GoogleAuthUiClient
import com.aminuolawale.muffassa.presentation.signin.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CorpusViewModel @Inject constructor(
    googleAuthUiClient: GoogleAuthUiClient,
    private val corpusRepository: CorpusRepository,
    savedStateHandle: SavedStateHandle,
) :
    ViewModel() {
    var userData: UserData? = null
    private val _state = MutableStateFlow(CorpusViewState())
    val state = _state.asStateFlow()

    init {

        userData = googleAuthUiClient.getSignedInUser()

    }

    fun initialize(corpusId: String?) {
            corpusId?.let {
                viewModelScope.launch {
                    val corpus = corpusRepository.getCorpus(it)
                    _state.update { it.copy(corpus = corpus) }
                }
            }
    }

    fun onEvent(corpusEvent: CorpusEvent) {
        when (corpusEvent) {
            is CorpusEvent.TitleChanged -> {
                _state.update {
                    it.copy(
                        corpus = it.corpus?.copy(title = corpusEvent.value)
                    )
                }
            }
        }
    }
}