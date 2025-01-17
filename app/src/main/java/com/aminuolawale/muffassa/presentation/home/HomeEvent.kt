package com.aminuolawale.muffassa.presentation.home

import com.aminuolawale.muffassa.domain.model.Corpus

sealed class HomeEvent {
    data object NewCorpus: HomeEvent()
    data class SaveCorpus(val corpus: Corpus): HomeEvent()
    data object DiscardCorpus : HomeEvent()
    data class ViewCorpus(val corpusId: String): HomeEvent()
    data class  AddSelection(val corpusId: String): HomeEvent()
    data class SelectionChange(val isSelecting: Boolean): HomeEvent()
    data object  EndSelection: HomeEvent()
    data class  DeleteCorpora(val selectionList: List<String>): HomeEvent()
}