package com.aminuolawale.muffassa.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminuolawale.muffassa.domain.model.Corpus
import com.aminuolawale.muffassa.domain.repository.CorpusRepository
import com.aminuolawale.muffassa.presentation.signin.GoogleAuthUiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val corpusRepository: CorpusRepository,
    private val googleAuthUiClient: GoogleAuthUiClient
) :
    ViewModel() {
    private var _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    private var _viewEffect = MutableSharedFlow<HomeViewEffect>()
    val viewEffect = _viewEffect.asSharedFlow()

    init {
        viewModelScope.launch {
            corpusRepository.getCorpora()
                .collect { corpusList -> _state.update { it.copy(corpusList = corpusList) } }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.NewCorpus ->
                googleAuthUiClient.getSignedInUser()?.userId?.let {
                    viewModelScope.launch {
                        val corpusId = corpusRepository.insertCorpus(
                            Corpus(
                                title = "untitled",
                                creatorUserId = it
                            )
                        )
                        _viewEffect.emit(HomeViewEffect.ViewCorpus(corpusId))
                    }
                }

           is HomeEvent.ViewCorpus -> viewModelScope.launch {
                _viewEffect.emit(HomeViewEffect.ViewCorpus(event.corpusId))
            }
        }
    }

}