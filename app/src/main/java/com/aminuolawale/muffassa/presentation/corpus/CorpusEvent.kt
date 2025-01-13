package com.aminuolawale.muffassa.presentation.corpus

sealed class CorpusEvent {
    data  object BeginEdit: CorpusEvent()
    data class TitleChanged(val value: String):CorpusEvent()
    data class DescriptionChanged(val value: String): CorpusEvent()
    data object EndEdit: CorpusEvent()
    data object ToggleNavDrawer: CorpusEvent()
    data class NavDrawer(val open: Boolean): CorpusEvent()
    data object ToggleOptionsMenu: CorpusEvent()
    data class OptionsMenu(val open:Boolean): CorpusEvent()
}