package com.aminuolawale.muffassa.presentation.newresource

import com.aminuolawale.muffassa.domain.model.Resource
import com.aminuolawale.muffassa.domain.model.ResourceType

data class NewResourceViewState(
    val resource: Resource? = null,
    val type: ResourceType = ResourceType.NOTE,
    val errors: Map<FormField, List<String>> = mapOf(),
)

data class FormError(
    val field: FormField,
    val message: String
)

enum class FormField {
    NAME,
    DESCRIPTION,
    DATA,
}