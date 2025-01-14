package com.aminuolawale.muffassa.presentation.corpus.utils

import com.aminuolawale.muffassa.domain.model.Corpus
import com.aminuolawale.muffassa.presentation.components.FormField

class CorpusValidator(private val corpus: Corpus) {

    private var errors: Map<FormField, List<String>> = mapOf()


    fun getErrors(): Map<FormField, List<String>>? {
        validate()
        return if (errors.any { it.value.isNotEmpty() }) errors else null
    }

    private fun validate(): Boolean {
        errors = mapOf(FormField.TITLE to validateTitle())
        return errors.all { it.value.isEmpty() }
    }


    private fun validateTitle(): List<String> {
        var errorMessages = mutableListOf<String>()
        if (corpus.title.isBlank()) {
            errorMessages = errorMessages.plus("Title cannot be blank").toMutableList()
        }
        return errorMessages
    }
}