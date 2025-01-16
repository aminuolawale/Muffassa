package com.aminuolawale.muffassa.presentation.corpus

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.corpus.quiz.QuizScreen
import com.aminuolawale.muffassa.presentation.corpus.resources.ResourcesScreen
import com.aminuolawale.muffassa.presentation.corpus.settings.SettingsScreen
import com.aminuolawale.muffassa.presentation.corpus.snippets.SnippetsScreen

@Composable
fun CorpusRoute(
    navController: NavController,
    corpusViewModel: CorpusViewModel,
    corpusId: String?,
    tab: String?,
) {
    corpusViewModel.onEvent(CorpusEvent.NavDrawer(open = false))
    corpusId?.let { nonNullCorpusId ->
        val corpusTab = CorpusTab.entries.find { it.name.lowercase() == tab } ?: CorpusTab.HOME
        corpusViewModel.initialize(nonNullCorpusId, corpusTab)
        when (corpusTab) {
            CorpusTab.HOME -> CorpusScreen(navController, corpusViewModel)
            CorpusTab.QUIZ -> QuizScreen(navController, corpusViewModel)
            CorpusTab.SNIPPETS -> SnippetsScreen(navController, corpusViewModel)
            CorpusTab.RESOURCES -> ResourcesScreen(navController, corpusViewModel)
            CorpusTab.SETTINGS -> SettingsScreen(navController,corpusViewModel)
        }
    }
}