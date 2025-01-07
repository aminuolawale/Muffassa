package com.aminuolawale.muffassa.presentation.home

sealed class HomeViewEffect {
    data object NoViewEffect: HomeViewEffect()
    data class ViewCorpus(val corpusId: Long): HomeViewEffect()
}