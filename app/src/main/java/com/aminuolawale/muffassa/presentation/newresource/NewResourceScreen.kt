package com.aminuolawale.muffassa.presentation.newresource

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aminuolawale.muffassa.domain.model.ResourceData
import com.aminuolawale.muffassa.domain.model.ResourceType
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewResourceScreen(
    navController: NavController,
    viewModel: NewResourceViewModel,
    corpusId: String?
) {
    LaunchedEffect(key1 = true) {
        viewModel.viewEffect.collectLatest {
            when (it) {
                NewResourceViewEffect.NoViewEffect -> {}
                NewResourceViewEffect.Saved -> {
                    navController.popBackStack()
                }
            }
        }
    }
    corpusId?.let { nonNullCorpusId ->
        viewModel.initialize(nonNullCorpusId)
        viewModel.state.collectAsState().let { state ->
            NewResourceScaffold(navController = navController, viewModel) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp, 120.dp, 20.dp, 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = state.value.type == ResourceType.NOTE,
                                onClick = {
                                    viewModel.onEvent(
                                        NewResourceEvent.SwitchResourceType(
                                            ResourceType.NOTE
                                        )
                                    )
                                })
                            Text("Note")
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = state.value.type == ResourceType.ARTICLE,
                                onClick = {
                                    viewModel.onEvent(
                                        NewResourceEvent.SwitchResourceType(
                                            ResourceType.ARTICLE
                                        )
                                    )
                                })
                            Text("Article")
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = state.value.type == ResourceType.FILE,
                                onClick = {
                                    viewModel.onEvent(
                                        NewResourceEvent.SwitchResourceType(
                                            ResourceType.FILE
                                        )
                                    )
                                })
                            Text("File")
                        }
                    }
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.value.resource?.name ?: "",
                        onValueChange = { viewModel.onEvent(NewResourceEvent.NameChanged(it)) },
                        label = { Text("Name") },
                        shape = RoundedCornerShape(20.dp),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.value.resource?.description ?: "",
                        onValueChange = { viewModel.onEvent(NewResourceEvent.DescriptionChanged(it)) },
                        label = { Text("Description") },
                        minLines = 2,
                        shape = RoundedCornerShape(20.dp),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    when (state.value.type) {
                        ResourceType.ARTICLE -> {

                        }

                        ResourceType.FILE -> {
                            DocumentPicker(onFileSelected = {
                                viewModel.onEvent(
                                    NewResourceEvent.FileSelected(
                                        it
                                    )
                                )
                            })
                        }

                        ResourceType.NOTE -> {
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = state.value.resource?.data?.value ?: "",
                                onValueChange = {
                                    viewModel.onEvent(
                                        NewResourceEvent.ResourceDataChanged(
                                            ResourceData(type = state.value.type, value = it)
                                        )
                                    )
                                },
                                label = { Text("Note") },
                                minLines = 5,
                                shape = RoundedCornerShape(20.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}