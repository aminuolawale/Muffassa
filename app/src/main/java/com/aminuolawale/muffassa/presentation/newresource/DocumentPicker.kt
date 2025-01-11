package com.aminuolawale.muffassa.presentation.newresource

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable


@Composable
fun DocumentPicker(onFileSelected: (Uri?) -> Unit) {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { result ->
            onFileSelected(result)
        }
    IconButton(onClick = { launcher.launch(arrayOf("application/txt")) }) {
        Icon(Icons.Outlined.Add, contentDescription = "Select Document")
    }
}