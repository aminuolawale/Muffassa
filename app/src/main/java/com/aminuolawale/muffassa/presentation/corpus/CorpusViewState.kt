package com.aminuolawale.muffassa.presentation.corpus

import com.aminuolawale.muffassa.domain.model.Corpus

data class CorpusViewState(
    val corpus: Corpus? = null,
    val editState: CorpusEditState = CorpusEditState.NONE,
    val activeTab: CorpusTab = CorpusTab.RESOURCES,
)

enum class CorpusEditState {
    NONE,
    TITLE,
    DESCRIPTION,
}

enum class CorpusTab {
    RESOURCES,
    ARTEFACTS,
}