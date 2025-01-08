package com.aminuolawale.muffassa.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aminuolawale.muffassa.domain.model.Corpus
import com.aminuolawale.muffassa.presentation.home.HomeEvent
import com.aminuolawale.muffassa.presentation.home.HomeViewModel
import com.aminuolawale.muffassa.presentation.home.HomeViewState


@Composable
fun CorpusGrid(homeViewModel: HomeViewModel) {
    // Grid
    homeViewModel.state.collectAsState().let { state ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 100.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            item {
                Box(modifier = Modifier.padding(5.dp)) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .size(100.dp),
                        border = BorderStroke(1.dp, color = Color.Black),
                        onClick = { homeViewModel.onEvent(HomeEvent.NewCorpus) },
                    ) {
                    }
                    Text("new", modifier = Modifier.align(Alignment.Center))
                }
            }
            items(state.value.corpusList) { corpus ->
                CorpusCard(
                    corpus = corpus,
                    isSelected = isCorpusSelected(state.value, corpus),
                    onClick = {
                        if (state.value.isSelecting) homeViewModel.onEvent(
                            HomeEvent.AddSelection(corpus.id)
                        ) else homeViewModel.onEvent(HomeEvent.ViewCorpus(corpus.id))
                    },
                    onLongClick = { homeViewModel.onEvent(HomeEvent.AddSelection(corpus.id)) })
            }
        }
    }

}

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

fun isCorpusSelected(state: HomeViewState, corpus: Corpus): Boolean {
    return state.selectionList.contains(corpus.id)
}