package com.baegoon.protocol.validator

import com.google.protobuf.Descriptors

class MaxLengthValidator : Validator {

    override fun <T> validate(
        fieldName: String,
        fieldValue: T,
        rule: Map.Entry<Descriptors.FieldDescriptor, Any>
    ): Boolean {
        println("${fieldValue.toString().length} :::::::::: ${rule.value.toString().length}")
        return this.check(
            isValidated = fieldValue.toString().length <= rule.value.toString().toInt(),
            fieldName = fieldName,
            errorMessage = "너무 길다."
        )
    }
}
