package com.aminuolawale.muffassa.presentation.newresource.utils

import com.aminuolawale.muffassa.domain.model.Resource
import com.aminuolawale.muffassa.presentation.newresource.FormField

class ResourceValidator(private val resource: Resource) {

    // Should not be read without ::validate being called.
    var errors : Map<FormField, List<String>> = mapOf()

    fun validate(): Boolean {
        errors = mapOf(FormField.NAME to validateName(), FormField.DATA to validateData())
        return errors.all { it.value.isEmpty() }
    }


    private fun validateName(): List<String> {
        var errorMessages = mutableListOf<String>()
        if (resource.name.isBlank()) {
          errorMessages =  errorMessages.plus("Name cannot be blank").toMutableList()
        }
        return errorMessages
    }

    private fun validateData(): List<String> {
        var errorMessages = mutableListOf<String>()
        if (resource.data == null) {
            errorMessages = errorMessages.plus("Data cannot be blank").toMutableList()
        }
        return errorMessages
    }
}