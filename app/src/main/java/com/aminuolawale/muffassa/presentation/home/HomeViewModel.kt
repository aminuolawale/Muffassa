package com.aminuolawale.muffassa.presentation.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminuolawale.muffassa.domain.model.Corpus
import com.aminuolawale.muffassa.domain.repository.CorpusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val corpusRepository: CorpusRepository
) :
    ViewModel() {
    private var _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    private var _viewEffect = MutableSharedFlow<HomeViewEffect>()
    val viewEffect = _viewEffect.asSharedFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.NewCorpus -> viewModelScope.launch {
                _viewEffect.emit(HomeViewEffect.NewCorpus)
            }
        }
    }

}