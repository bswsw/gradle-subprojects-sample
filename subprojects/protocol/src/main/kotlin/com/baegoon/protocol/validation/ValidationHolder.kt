package com.baegoon.protocol.validation

import com.baegoon.protocol.validation.validator.Validator

/**
 * validator 와 errorMessage 를 매핑한다.
 * @param validator
 * @param errorMessage
 */
data class ValidationHolder(
    val validator: Validator,
    val errorMessage: String
)
