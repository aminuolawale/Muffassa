package com.aminuolawale.muffassa.presentation.corpus.resources

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aminuolawale.muffassa.domain.model.Resource
import com.aminuolawale.muffassa.presentation.utils.Utils


@Composable
fun ResourceItem(resource: Resource) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Menu, contentDescription = "Note", modifier = Modifier.fillMaxWidth(0.2f))
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth(0.80F)) {
            Text(resource.name, fontSize = 18.sp)
            Text(resource.description, fontSize = 12.sp)

        }
        Text(Utils.formatDate(resource.lastUpdated), fontSize = 10.sp, modifier = Modifier.fillMaxWidth())
    }
}

