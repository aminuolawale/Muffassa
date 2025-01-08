package com.aminuolawale.muffassa.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aminuolawale.muffassa.domain.model.Corpus

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CorpusCard(
    corpus: Corpus,
    isSelected: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    Box(modifier = Modifier.padding(5.dp)) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .size(100.dp)
                .combinedClickable(
                    onClick = onClick,
                    onLongClick = onLongClick
                ),
            border = BorderStroke(1.dp, color = Color.Black),
            color = if (isSelected) Color.Red else Color.White
        ) {
        }
        Text(corpus.title, modifier = Modifier.align(Alignment.Center))
    }
}