package com.aminuolawale.muffassa.presentation.corpus

import com.aminuolawale.muffassa.domain.model.Corpus
import com.aminuolawale.muffassa.domain.model.Resource

data class CorpusViewState(
    val corpus: Corpus? = null,
    val editState: CorpusEditState = CorpusEditState.NONE,
    val activeTab: CorpusTab = CorpusTab.RESOURCES,
    val resources : List<Resource> = emptyList(),
)

enum class CorpusEditState {
    NONE,
    TITLE,
    DESCRIPTION,
}

enum class CorpusTab {
     HOME,
    QUIZ,
    SNIPPETS,
    RESOURCES,
}