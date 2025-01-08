package com.aminuolawale.muffassa.presentation.corpus

import com.aminuolawale.muffassa.domain.model.Corpus

data class CorpusViewState(
    val corpus: Corpus? = null,
    val editState: CorpusEditState = CorpusEditState.NONE,
)

enum class CorpusEditState {
    NONE,
    TITLE,
}