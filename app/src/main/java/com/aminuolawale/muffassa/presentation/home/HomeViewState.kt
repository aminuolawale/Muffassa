package com.aminuolawale.muffassa.presentation.home

import com.aminuolawale.muffassa.domain.model.Corpus

data class HomeViewState(
    val corpusList: List<Corpus> = emptyList(),
    val isSelecting: Boolean = false,
    val selectionList: Set<String> = setOf(),
    val isSearching: Boolean = false,
)
