package com.aminuolawale.muffassa.presentation.corpus

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.corpus.components.CorpusScaffold
import com.aminuolawale.muffassa.presentation.corpus.home.HomeTab
import com.aminuolawale.muffassa.presentation.corpus.quiz.QuizTab
import com.aminuolawale.muffassa.presentation.corpus.resources.ResourcesTab
import com.aminuolawale.muffassa.presentation.corpus.snippets.SnippetsTab

@Composable
fun CorpusScreen(navController: NavController, viewModel: CorpusViewModel) {
    viewModel.state.collectAsState().let { state ->
        CorpusScaffold(navController, viewModel) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp, 60.dp, 20.dp, 0.dp)
            ) {
                when (state.value.activeTab) {
                    CorpusTab.HOME -> HomeTab(viewModel)
                    CorpusTab.QUIZ -> QuizTab()
                    CorpusTab.SNIPPETS -> SnippetsTab()
                    CorpusTab.RESOURCES -> ResourcesTab()
                }

            }
        }
    }
}

@Composable
fun TitleArea(
    text: String,
    isEditing: Boolean,
    onClick: () -> Unit,
    onValueChange: (value: String) -> Unit
) {
    if (isEditing) {
        TextField(modifier = Modifier.padding(0.dp),
            value = text,
            textStyle = TextStyle(fontSize = 36.sp),
            onValueChange = { onValueChange.invoke(it) })
    } else {
        Text(
            text = text,
            fontSize = 36.sp,
            modifier = Modifier.clickable {
                onClick.invoke()
            })
    }
}

@Composable
fun DescriptionArea(
    modifier: Modifier,
    text: String,
    isEditing: Boolean,
    onClick: () -> Unit,
    onValueChange: (value: String) -> Unit
) {
    if (isEditing) {
        TextField(modifier = modifier, value = text, onValueChange = { onValueChange.invoke(it) })
    } else {
        Text(text = text, modifier = modifier.clickable { onClick.invoke() })
    }
}

@Composable
fun ContentArea() {
    Text("Artefacts")
}