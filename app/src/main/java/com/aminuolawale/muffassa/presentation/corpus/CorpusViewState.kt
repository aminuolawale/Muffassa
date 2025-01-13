package com.aminuolawale.muffassa.presentation.corpus

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import com.aminuolawale.muffassa.domain.model.Corpus
import com.aminuolawale.muffassa.domain.model.Resource

data class CorpusViewState(
    val corpus: Corpus? = null,
    val editState: CorpusEditState = CorpusEditState.NONE,
    val activeTab: CorpusTab = CorpusTab.RESOURCES,
    val resources : List<Resource> = emptyList(),
    val drawerState: DrawerState = DrawerState(DrawerValue.Closed),
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
    SETTINGS,
}