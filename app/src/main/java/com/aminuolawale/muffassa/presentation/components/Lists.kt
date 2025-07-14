package com.aminuolawale.muffassa.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
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

@Composable
fun <T> FunctionalList(
    listItems: List<T>,
    itemKey: (T) -> String,
    onViewItem: (T) -> Unit,
    onSelectionChange: (Boolean) -> Unit,
    selectionActions: List<@Composable (List<String>) -> Unit>,
    modifier: Modifier,
    itemComposable: @Composable (T) -> Unit
) {
    var selectionList by remember { mutableStateOf(setOf<String>()) }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp, 0.dp, 10.dp, 0.dp)
        ) {
            items(listItems) { listItem ->
                FunctionalListItem(
                    listItem,
                    selectionList.contains(itemKey(listItem)),
                    onItemClick = {
                        if (selectionList.isNotEmpty()) {
                            selectionList = if (selectionList.contains(itemKey(listItem))) {
                                selectionList.minus(itemKey(listItem))
                            } else {
                                selectionList.plus(itemKey(listItem))
                            }
                        } else {
                            onViewItem(listItem)
                        }
                        onSelectionChange(selectionList.isNotEmpty())
                    },
                    onItemLongClick = {
                        selectionList = selectionList.plus(itemKey(listItem))
                        onSelectionChange(selectionList.isNotEmpty())

                    }) {
                    itemComposable(listItem)
                }
            }
        }
        if (selectionList.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    selectionList = setOf()
                    onSelectionChange(false)
                }) {
                    Icon(Icons.Default.Close, contentDescription = "Cancel")
                }
                selectionActions.map { action ->
                    action(selectionList.toList())
                }
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
                onLongClick = { onItemLongClick(listItem) })
            .clip(RoundedCornerShape(20.dp)),
        color = if (isSelected) Color.LightGray else Color.Transparent
    ) {
        itemComposable(listItem)
    }
}