package com.aminuolawale.muffassa.presentation.newresource

import com.aminuolawale.muffassa.domain.model.Resource

sealed class NewResourceViewEffect {
    data class Saved(val resource: Resource): NewResourceViewEffect()
    data object NoViewEffect: NewResourceViewEffect()
}