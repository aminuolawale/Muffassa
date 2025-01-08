package com.aminuolawale.muffassa.presentation.home

sealed class HomeEvent {
    data object NewCorpus: HomeEvent()
    data class ViewCorpus(val corpusId: String): HomeEvent()
    data class  AddSelection(val corpusId: String): HomeEvent()
    data object  EndSelection: HomeEvent()
    data object  DeleteCorpora: HomeEvent()
    data object BeginSearch: HomeEvent()
}