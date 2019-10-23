package com.baegoon.protocol.validator

import com.google.protobuf.Descriptors

class MinLengthValidator : Validator {

    override fun <T> validate(
        fieldName: String,
        fieldValue: T,
        rule: Map.Entry<Descriptors.FieldDescriptor, Any>
    ): Boolean {
        return this.check(
            isValidated = fieldValue.toString().length >= rule.value.toString().toInt(),
            fieldName = fieldName,
            errorMessage = "너무 짧다."
        )
    }
}
