package com.aminuolawale.muffassa.presentation.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aminuolawale.muffassa.presentation.home.HomeEvent
import com.aminuolawale.muffassa.presentation.home.HomeViewModel


@Composable
fun CorpusList(homeViewModel: HomeViewModel) {
    homeViewModel.state.collectAsState().let { state ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            contentPadding = PaddingValues(0.dp, 60.dp, 0.dp, 0.dp), userScrollEnabled = true,
        ) {
            items(state.value.corpusList) { corpus ->
                CorpusItem(
                    corpus = corpus,
                    isSelected = state.value.selectionList.contains(corpus.id),
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



