package com.aminuolawale.muffassa.presentation.corpus.resources


sealed class ResourcesEvent {
    data class SelectionChange(val isSelecting: Boolean): ResourcesEvent()
}