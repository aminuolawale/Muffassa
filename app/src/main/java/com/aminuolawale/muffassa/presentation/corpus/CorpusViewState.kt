package com.aminuolawale.muffassa.presentation.corpus

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import com.aminuolawale.muffassa.domain.model.Corpus
import com.aminuolawale.muffassa.domain.model.Resource

data class CorpusViewState(
    val corpus: Corpus? = null,
    val isEditing: Boolean = false,
    val activeTab: CorpusTab = CorpusTab.RESOURCES,
    val resources : List<Resource> = emptyList(),
    val drawerState: DrawerState = DrawerState(DrawerValue.Closed),
    val optionsMenu: Boolean = false,
)

enum class CorpusTab {
     HOME,
    QUIZ,
    SNIPPETS,
    RESOURCES,
    SETTINGS,
}