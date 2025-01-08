package com.aminuolawale.muffassa.presentation.corpus

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aminuolawale.muffassa.presentation.components.ContentWithBottomBar

@Composable
fun CorpusScreen(navController: NavController, corpusViewModel: CorpusViewModel) {
    corpusViewModel.state.collectAsState().let {
        ContentWithBottomBar(
            navController,
            userData = corpusViewModel.userData,
            onClick = { corpusViewModel.onEvent(CorpusEvent.EndEdit) }) {
            Column(modifier = Modifier.fillMaxSize().padding(20.dp, 60.dp, 20.dp, 0.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (it.value.editState == CorpusEditState.TITLE) {
                        TextField(modifier = Modifier.padding(0.dp),
                            value = it.value.corpus?.title ?: "Untitled",
                            textStyle = TextStyle(fontSize = 36.sp),
                            onValueChange = {
                                corpusViewModel.onEvent(CorpusEvent.TitleChanged(it))
                            })
                    } else {
                        Text(
                            text = it.value.corpus?.title ?: "Untitled",
                            fontSize = 36.sp,
                            modifier = Modifier.clickable {
                                corpusViewModel.onEvent(
                                    CorpusEvent.BeginEdit(
                                        CorpusEditState.TITLE
                                    )
                                )
                            })
                    }
                }

            }
        }
    }
}