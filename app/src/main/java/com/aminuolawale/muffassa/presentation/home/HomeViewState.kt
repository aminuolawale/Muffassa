package com.aminuolawale.muffassa.presentation.home

import com.aminuolawale.muffassa.domain.model.Corpus
import com.aminuolawale.muffassa.presentation.signin.UserData

data class HomeViewState(
    val userData: UserData?,
    val corpusList: List<Corpus> = emptyList(),
    val isSelecting: Boolean = false,
    val selectionList: Set<String> = setOf(),
    val isSearching: Boolean = false,
)
