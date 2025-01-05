package com.aminuolawale.muffassa.presentation.home

sealed class HomeEvent {
    data object NewCorpus: HomeEvent()
    data class ViewCorpus(val corpusId: Long): HomeEvent()
}