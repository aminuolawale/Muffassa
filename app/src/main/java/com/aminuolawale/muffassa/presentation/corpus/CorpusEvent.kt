package com.aminuolawale.muffassa.presentation.corpus

sealed class CorpusEvent {
    data class TitleChanged(val value: String):CorpusEvent()
}