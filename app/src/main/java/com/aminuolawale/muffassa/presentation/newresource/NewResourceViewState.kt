package com.aminuolawale.muffassa.presentation.newresource

import com.aminuolawale.muffassa.domain.model.Resource
import com.aminuolawale.muffassa.domain.model.ResourceType
import com.aminuolawale.muffassa.presentation.components.FormField

data class NewResourceViewState(
    val resource: Resource? = null,
    val type: ResourceType = ResourceType.NOTE,
    val errors: Map<FormField, List<String>> = mapOf(),
)

