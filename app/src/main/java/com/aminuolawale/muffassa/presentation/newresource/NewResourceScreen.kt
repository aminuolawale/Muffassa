package com.aminuolawale.muffassa.presentation.newresource

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aminuolawale.muffassa.domain.model.ResourceData
import com.aminuolawale.muffassa.domain.model.ResourceType
import com.aminuolawale.muffassa.presentation.Screen
import com.aminuolawale.muffassa.presentation.corpus.CorpusTab
import com.aminuolawale.muffassa.presentation.newresource.components.DescriptionField
import com.aminuolawale.muffassa.presentation.newresource.components.NameField
import com.aminuolawale.muffassa.presentation.newresource.components.NewResourceScaffold
import com.aminuolawale.muffassa.presentation.newresource.components.NoteDataField
import com.aminuolawale.muffassa.presentation.newresource.components.ResourceTypeSelector
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewResourceScreen(
    navController: NavController,
    viewModel: NewResourceViewModel,
    corpusId: String?,
) {
    LaunchedEffect(key1 = true) {
        viewModel.viewEffect.collectLatest { viewEffect ->
            when (viewEffect) {
                NewResourceViewEffect.NoViewEffect -> {}
                is NewResourceViewEffect.Saved -> navController.navigate(Screen.CorpusHome.route + "?corpusId=${viewEffect.resource.corpusId}&activeTab=${CorpusTab.RESOURCES.ordinal}")
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
                    ResourceTypeSelector(state = state.value, onNoteClick = {
                        viewModel.onEvent(
                            NewResourceEvent.SwitchResourceType(
                                ResourceType.NOTE
                            )
                        )
                    }, onFileClick = {
                        viewModel.onEvent(
                            NewResourceEvent.SwitchResourceType(
                                ResourceType.FILE
                            )
                        )
                    }, onArticleClick = {
                        viewModel.onEvent(
                            NewResourceEvent.SwitchResourceType(
                                ResourceType.ARTICLE
                            )
                        )
                    })

                    NameField(
                        state = state.value,
                        onValueChange = { viewModel.onEvent(NewResourceEvent.NameChanged(it)) },
                        onFocusChange = {
                            viewModel.onEvent(
                                NewResourceEvent.FormFieldFocusChanged(it)
                            )
                        })

                    Spacer(modifier = Modifier.height(20.dp))
                    DescriptionField(
                        state = state.value,
                        onValueChange = { viewModel.onEvent(NewResourceEvent.DescriptionChanged(it)) })
                    Spacer(modifier = Modifier.height(20.dp))

                    when (state.value.type) {
                        ResourceType.ARTICLE -> {}
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
                            NoteDataField(
                                state = state.value,
                                onChange = {
                                    viewModel.onEvent(
                                        NewResourceEvent.ResourceDataChanged(
                                            ResourceData(type = state.value.type, value = it)
                                        )
                                    )
                                },
                                onFocusChange = {
                                    viewModel.onEvent(
                                        NewResourceEvent.FormFieldFocusChanged(it)
                                    )
                                })
                        }

                    }
                }
            }
        }
    }
}
