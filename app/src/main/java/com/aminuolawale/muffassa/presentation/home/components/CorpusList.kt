package com.aminuolawale.muffassa.presentation.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aminuolawale.muffassa.domain.model.Corpus
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

@Composable
fun NewCorpusList(homeViewModel: HomeViewModel) {
    homeViewModel.state.collectAsState().let { state ->
        FunctionalList(
            listItems = homeViewModel.state.value.corpusList,
            itemKeyGetter = { it.id },
            onViewItem = {
                if (state.value.isSelecting) homeViewModel.onEvent(
                    HomeEvent.AddSelection(it.id)
                ) else homeViewModel.onEvent(HomeEvent.ViewCorpus(it.id))
            }, onSelectionChange = { homeViewModel.onEvent(HomeEvent.SelectionChange(it)) }) {
            NewCorpusItem(it)
        }
    }
}

@Composable
fun NewCorpusItem(corpus: Corpus) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column {
            Text(text = corpus.title, fontSize = 24.sp)
            Text(text = corpus.description)
        }

    }
}

@Composable
fun <T> FunctionalList(
    listItems: List<T>,
    itemKeyGetter: (T) -> String,
    onViewItem: (T) -> Unit,
    onSelectionChange: (Boolean) -> Unit,
    itemComposable: @Composable (T) -> Unit
) {
    var selectionList by remember { mutableStateOf(setOf<String>()) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 60.dp, 10.dp, 0.dp)
    ) {
        items(listItems) { listItem ->
            FunctionalListItem(
                listItem,
                selectionList.contains(itemKeyGetter(listItem)),
                onItemClick = {
                    if (selectionList.isNotEmpty()) {
                        selectionList = if (selectionList.contains(itemKeyGetter(listItem))) {
                            selectionList.minus(itemKeyGetter(listItem))
                        } else {
                            selectionList.plus(itemKeyGetter(listItem))
                        }
                    } else {
                        onViewItem(listItem)
                    }
                    onSelectionChange(selectionList.isNotEmpty())
                },
                onItemLongClick = {
                    selectionList = selectionList.plus(itemKeyGetter(listItem))
                    onSelectionChange(selectionList.isNotEmpty())

                }
            ) {
                itemComposable(listItem)
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> FunctionalListItem(
    listItem: T,
    isSelected: Boolean,
    onItemClick: (T) -> Unit,
    onItemLongClick: (T) -> Unit,
    itemComposable: @Composable (T) -> Unit
) {
    Surface(
        modifier = Modifier
            .combinedClickable(
                onClick = { onItemClick(listItem) },
                onLongClick = { onItemLongClick(listItem) }
            )
            .clip(RoundedCornerShape(20.dp)),
        color = if (isSelected) Color.LightGray else Color.Transparent
    ) {
        itemComposable(listItem)
    }
}



