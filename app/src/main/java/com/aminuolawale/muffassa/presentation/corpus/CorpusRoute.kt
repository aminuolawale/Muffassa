package com.aminuolawale.muffassa.presentation.corpus

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.corpus.quiz.QuizTab
import com.aminuolawale.muffassa.presentation.corpus.resources.ResourcesScreen
import com.aminuolawale.muffassa.presentation.corpus.snippets.SnippetsTab

@Composable
fun CorpusRoute(
    navController: NavController,
    corpusViewModel: CorpusViewModel,
    corpusId: String?,
    tab: String?
) {
    corpusViewModel.onEvent(CorpusEvent.NavDrawer(open = false))
    corpusId?.let { nonNullCorpusId ->
        val corpusTab = CorpusTab.entries.find { it.name.lowercase() == tab } ?: CorpusTab.HOME
        corpusViewModel.initialize(nonNullCorpusId, corpusTab)
        when (corpusTab) {
            CorpusTab.HOME -> CorpusScreen(navController, corpusViewModel)
            CorpusTab.QUIZ -> QuizTab()
            CorpusTab.SNIPPETS -> SnippetsTab()
            CorpusTab.RESOURCES -> ResourcesScreen(navController, corpusViewModel)
        }
    }
}