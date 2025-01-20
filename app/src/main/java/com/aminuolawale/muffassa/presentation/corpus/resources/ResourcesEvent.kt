package com.aminuolawale.muffassa.presentation.corpus.resources


sealed class ResourcesEvent {
    data class SelectionChange(val isSelecting: Boolean): ResourcesEvent()
    data class DeleteResources(val ids: List<String>): ResourcesEvent()
}