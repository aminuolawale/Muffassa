package com.aminuolawale.muffassa.presentation.corpus

sealed class CorpusViewEffect {
    data object NoViewEffect: CorpusViewEffect()
    data class  SelectTab(val tab: CorpusTab): CorpusViewEffect()
}