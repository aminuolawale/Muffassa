package com.aminuolawale.muffassa.presentation.newresource

sealed class NewResourceViewEffect {
    data object Saved: NewResourceViewEffect()
    data object NoViewEffect: NewResourceViewEffect()
}