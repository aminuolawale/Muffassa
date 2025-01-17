package com.aminuolawale.muffassa.presentation.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.aminuolawale.muffassa.presentation.components.FunctionalList
import com.aminuolawale.muffassa.presentation.home.HomeEvent
import com.aminuolawale.muffassa.presentation.home.HomeViewModel


@Composable
fun CorpusList(homeViewModel: HomeViewModel) {
    FunctionalList(
        listItems = homeViewModel.state.value.corpusList,
        itemKey = { it.id },
        onViewItem = {
            homeViewModel.onEvent(HomeEvent.ViewCorpus(it.id))
        },
        onSelectionChange = { homeViewModel.onEvent(HomeEvent.SelectionChange(it)) },
        selectionActions = listOf {
            IconButton(onClick = { homeViewModel.onEvent(HomeEvent.DeleteCorpora(it)) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    ) {
        CorpusItem(it)
    }
}

@Composable
fun CorpusItem(corpus: Corpus) {
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



