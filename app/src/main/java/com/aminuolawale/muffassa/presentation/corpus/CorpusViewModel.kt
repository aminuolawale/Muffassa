package com.aminuolawale.muffassa.presentation.corpus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminuolawale.muffassa.domain.repository.CorpusRepository
import com.aminuolawale.muffassa.domain.repository.ResourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CorpusViewModel @Inject constructor(
    private val corpusRepository: CorpusRepository,
    private val resourceRepository: ResourceRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(CorpusViewState())
    val state = _state.asStateFlow()

    fun initialize(corpusId: String?, tab: Int?) {
        val activeTab = tab?.let { CorpusTab.entries[it] }?:CorpusTab.HOME
        corpusId?.let {
            viewModelScope.launch {
                val corpus = corpusRepository.getCorpus(it)
                _state.update { it.copy(corpus = corpus, activeTab = activeTab) }
                resourceRepository.getResources(it).collect { resourceList ->
                    _state.update { it.copy(resources = resourceList) }
                }
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

            is CorpusEvent.SelectTab -> {
                _state.update { it.copy(activeTab = corpusEvent.tab) }
            }
        }
    }
}