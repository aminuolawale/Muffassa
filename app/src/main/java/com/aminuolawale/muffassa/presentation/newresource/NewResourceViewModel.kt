package com.aminuolawale.muffassa.presentation.newresource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminuolawale.muffassa.domain.model.Resource
import com.aminuolawale.muffassa.domain.repository.ResourceRepository
import com.aminuolawale.muffassa.presentation.corpus.CorpusTab
import com.aminuolawale.muffassa.presentation.newresource.utils.ResourceValidator
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
class NewResourceViewModel @Inject constructor(private val resourceRepository: ResourceRepository) :
    ViewModel() {

    private var _state = MutableStateFlow(NewResourceViewState(resource = getDefaultResource()))
    val state = _state.asStateFlow()

    private var _viewEffect = MutableSharedFlow<NewResourceViewEffect>()
    val viewEffect = _viewEffect.asSharedFlow()

    fun initialize(corpusId: String) {
        _state.update { it.copy(resource = it.resource?.copy(corpusId = corpusId)) }
    }

    fun onEvent(newResourceEvent: NewResourceEvent) {
        when (newResourceEvent) {
            is NewResourceEvent.NameChanged -> {
                _state.update { it.copy(resource = it.resource?.copy(name = newResourceEvent.value)) }
            }

            is NewResourceEvent.DescriptionChanged -> {
                _state.update { it.copy(resource = it.resource?.copy(description = newResourceEvent.value)) }
            }

            NewResourceEvent.Save -> {
                _state.value.resource?.let { resource ->
                    val resourceValidator = ResourceValidator(resource)
                    if (resourceValidator.errors.isNotEmpty()) {
                        _state.update { it.copy(errors = resourceValidator.errors) }
                    } else {
                        viewModelScope.launch {
                            resourceRepository.insertResource(resource)
                            _viewEffect.emit(NewResourceViewEffect.Saved(resource))
                            reset()
                        }
                    }
                }
            }

            is NewResourceEvent.FileSelected -> {

            }

            is NewResourceEvent.SwitchResourceType -> {
                _state.update { it.copy(type = newResourceEvent.type) }
            }

            is NewResourceEvent.ResourceDataChanged -> {
                _state.update { it.copy(resource = it.resource?.copy(data = newResourceEvent.resourceData)) }
            }

            is NewResourceEvent.FormFieldFocusChanged -> {
                _state.update { it.copy(errors = mapOf()) }
            }
        }
    }

    private fun getDefaultResource(): Resource {
        return Resource(
            id = UUID.randomUUID().toString(),
            name = "",
            description = "",
            corpusId = "",
            data = null
        )

    }


    private fun reset() {
        _state.update { NewResourceViewState(resource = getDefaultResource()) }
    }

}