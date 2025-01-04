package com.aminuolawale.muffassa.presentation.home

import androidx.lifecycle.ViewModel
import com.aminuolawale.muffassa.domain.model.Corpus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class HomeViewModel : ViewModel() {
    private var _state = MutableStateFlow<List<Corpus>>(emptyList())
    val state = _state.asStateFlow()


    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.NewCorpus -> {
//                Toast.makeText(context, "New corpus", Toast.LENGTH_SHORT).show()
            }
        }
    }

}