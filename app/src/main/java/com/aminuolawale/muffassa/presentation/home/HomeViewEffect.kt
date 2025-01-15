package com.aminuolawale.muffassa.presentation.home

sealed class HomeViewEffect {
    data object NoViewEffect: HomeViewEffect()
    data class ViewCorpus(val corpusId: String, val isEditing:Boolean = false): HomeViewEffect()
}