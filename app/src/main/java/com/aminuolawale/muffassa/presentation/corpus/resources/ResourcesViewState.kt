package com.aminuolawale.muffassa.presentation.corpus.resources

import com.aminuolawale.muffassa.domain.model.Resource

data class ResourcesViewState(
    val resourcesList: List<Resource> = listOf(),
    val isSelecting: Boolean = false,
)