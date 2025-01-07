package com.aminuolawale.muffassa.presentation.home

sealed class HomeEvent {
    data object NewCorpus: HomeEvent()
    data class ViewCorpus(val corpusId: Long): HomeEvent()
    data class  AddSelection(val itemId: Long): HomeEvent()
    data object  EndSelection: HomeEvent()
    data object  DeleteCorpora: HomeEvent()
}