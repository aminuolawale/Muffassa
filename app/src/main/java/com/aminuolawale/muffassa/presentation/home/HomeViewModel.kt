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
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val corpusRepository: CorpusRepository,
    private val googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {
    private var _state = MutableStateFlow(HomeViewState())
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
            HomeEvent.NewCorpus -> googleAuthUiClient.getSignedInUser()?.userId?.let {
                viewModelScope.launch {
                    val corpusId = UUID.randomUUID().toString()
                    corpusRepository.insertCorpus(
                        Corpus(
                            id = corpusId, title = "Untitled", creatorUserId = it
                        )
                    )
                    _viewEffect.emit(HomeViewEffect.ViewCorpus(corpusId))
                }
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

            HomeEvent.DeleteCorpora -> {
                viewModelScope.launch {
                    corpusRepository.deleteCorpora(_state.value.selectionList.toList())
                    _state.update { it.copy(isSelecting = false) }
                }
            }

            HomeEvent.BeginSearch -> {
                _state.update { it.copy(isSearching = true) }
            }
        }
    }

    fun onViewEffectComplete() {
        viewModelScope.launch {
            _viewEffect.emit(HomeViewEffect.NoViewEffect)
        }

    }

}