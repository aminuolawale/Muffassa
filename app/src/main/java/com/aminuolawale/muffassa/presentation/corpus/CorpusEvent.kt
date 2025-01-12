package com.aminuolawale.muffassa.presentation.corpus

sealed class CorpusEvent {
    data  class BeginEdit(val editField: CorpusEditState): CorpusEvent()
    data class TitleChanged(val value: String):CorpusEvent()
    data class DescriptionChanged(val value: String): CorpusEvent()
    data object EndEdit: CorpusEvent()
    data class SelectTab(val tab: CorpusTab):CorpusEvent()
    data object OpenNavDrawer: CorpusEvent()
}