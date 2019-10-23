package com.baegoon.protocol.validator

import com.baegoon.protocol.exception.ProtoValidationException
import com.google.protobuf.Descriptors

interface Validator {

    @Throws(ProtoValidationException::class)
    fun <T> validate(fieldName: String, fieldValue: T, rule: Map.Entry<Descriptors.FieldDescriptor, Any>): Boolean

    fun check(isValidated: Boolean, fieldName: String, errorMessage: String): Boolean {
        if (!isValidated) {
            throw ProtoValidationException(fieldName, errorMessage)
        }

        return isValidated
    }
}
