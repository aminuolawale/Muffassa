package com.aminuolawale.muffassa.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {
    private var _state = MutableStateFlow(HomeViewState(googleAuthUiClient.getSignedInUser()))
    val state = _state.asStateFlow()

    private var _viewEffect = MutableSharedFlow<HomeViewEffect>()
    val viewEffect = _viewEffect.asSharedFlow()

    init {
        googleAuthUiClient.getSignedInUser()?.userId?.let {
            viewModelScope.launch {
                corpusRepository.getCorpora(it)
                    .collect { corpusList -> _state.update { it.copy(corpusList = corpusList.sortedBy { corpus -> -corpus.lastUpdated }) } }
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.NewCorpus -> {
                _state.update { it.copy(isCreating = true) }
            }

            is HomeEvent.ViewCorpus -> viewModelScope.launch {
                _viewEffect.emit(HomeViewEffect.ViewCorpus(event.corpusId))
            }

            is HomeEvent.AddSelection -> {
                var selectedItems = _state.value.selectionList
                var isSelecting = true
                if (selectedItems.contains(event.corpusId)) {
                    selectedItems = selectedItems.minus(event.corpusId)
                    isSelecting = selectedItems.isNotEmpty()
                } else {
                    selectedItems = selectedItems.plus(event.corpusId)
                }
                _state.update {
                    it.copy(isSelecting = isSelecting, selectionList = selectedItems)
                }
            }

            HomeEvent.EndSelection -> _state.update {
                it.copy(
                    isSelecting = false, selectionList = emptySet()
                )
            }

           is HomeEvent.DeleteCorpora -> {
                viewModelScope.launch {
                    corpusRepository.deleteCorpora(event.selectionList)
                    _state.update { it.copy(isSelecting = false) }
                }
            }

            HomeEvent.DiscardCorpus -> {
                _state.update { it.copy(isCreating = false) }
            }

            is HomeEvent.SaveCorpus -> {
                _state.update { it.copy(isCreating = false) }
                viewModelScope.launch {
                    corpusRepository.insertCorpus(event.corpus)
                    _viewEffect.emit(
                        HomeViewEffect.ViewCorpus(
                            event.corpus.id
                        )
                    )
                }
            }

            is HomeEvent.SelectionChange -> {
                _state.update { it.copy(isSelecting = event.isSelecting) }
            }
        }
    }

}