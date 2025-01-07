package com.aminuolawale.muffassa.presentation.home

import com.aminuolawale.muffassa.domain.model.Corpus
import com.google.common.collect.ImmutableSet

data class HomeViewState(
    val corpusList: List<Corpus> = emptyList(),
    val isSelecting: Boolean = false,
    val selectionList: Set<Long> = setOf()
)
