package com.baegoon.protocol.validation.validator

import com.google.protobuf.Descriptors

class MaxLengthValidator : Validator {

    override fun <T> validate(
        fieldName: String,
        fieldValue: T,
        rule: Map.Entry<Descriptors.FieldDescriptor, Any>
    ): Boolean {
        return fieldValue.toString().length <= rule.value.toString().toInt()
    }
}
