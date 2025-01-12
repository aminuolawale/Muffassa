package com.aminuolawale.muffassa.presentation.newresource

import android.net.Uri
import androidx.compose.ui.focus.FocusState
import com.aminuolawale.muffassa.domain.model.ResourceData
import com.aminuolawale.muffassa.domain.model.ResourceType

sealed class NewResourceEvent {
    data class NameChanged(val value: String) : NewResourceEvent()
    data class DescriptionChanged(val value: String) : NewResourceEvent()
    data object Save : NewResourceEvent()
    data class SwitchResourceType(val type: ResourceType) : NewResourceEvent()
    data class FileSelected(val uri: Uri?) : NewResourceEvent()
    data class ResourceDataChanged(val resourceData: ResourceData?):NewResourceEvent()
    data class FormFieldFocusChanged(val focusState: FocusState): NewResourceEvent()


}