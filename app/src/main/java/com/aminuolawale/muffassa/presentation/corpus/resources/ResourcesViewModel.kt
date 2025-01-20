package com.aminuolawale.muffassa.presentation.corpus.resources

import com.aminuolawale.muffassa.domain.repository.ResourceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


class ResourcesViewModel @Inject constructor(private val resourceRepository: ResourceRepository) {
    private val _state = MutableStateFlow(ResourcesViewState())
    val state = _state.asStateFlow()

    suspend fun initialize(corpusId: String) {
        resourceRepository.getResources(corpusId).collect { items ->
            _state.update { it.copy(resourcesList = items) }
        }
    }


    fun onEvent(resourcesEvent: ResourcesEvent){
        when(resourcesEvent){
            is ResourcesEvent.SelectionChange -> {
                _state.update { it.copy(isSelecting = resourcesEvent.isSelecting) }
            }
        }
    }
}