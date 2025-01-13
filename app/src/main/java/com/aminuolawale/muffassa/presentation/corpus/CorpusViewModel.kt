package com.aminuolawale.muffassa.presentation.corpus

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminuolawale.muffassa.domain.repository.CorpusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CorpusViewModel @Inject constructor(
    private val corpusRepository: CorpusRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(CorpusViewState())
    val state = _state.asStateFlow()

    fun initialize(corpusId: String, corpusTab: CorpusTab) {
        viewModelScope.launch {
            val corpus = corpusRepository.getCorpus(corpusId)
            _state.update { it.copy(corpus = corpus, activeTab = corpusTab) }
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

            is CorpusEvent.BeginEdit -> {
                _state.update { it.copy(editState = corpusEvent.editField) }
            }

            CorpusEvent.EndEdit -> {
                _state.update { it.copy(editState = CorpusEditState.NONE) }
                _state.value.corpus?.let {
                    viewModelScope.launch { corpusRepository.insertCorpus(it) }
                }
            }

            is CorpusEvent.DescriptionChanged -> {
                _state.update { it.copy(corpus = it.corpus?.copy(description = corpusEvent.value)) }
            }

            CorpusEvent.ToggleNavDrawer -> {
                _state.update {
                    it.copy(
                        drawerState = if (it.drawerState.isOpen) DrawerState(
                            DrawerValue.Closed
                        ) else DrawerState(DrawerValue.Open)
                    )
                }
            }

            is CorpusEvent.NavDrawer -> {
                _state.update {
                    it.copy(drawerState = DrawerState(if (corpusEvent.open) DrawerValue.Open else DrawerValue.Closed))
                }
            }
        }
    }
}