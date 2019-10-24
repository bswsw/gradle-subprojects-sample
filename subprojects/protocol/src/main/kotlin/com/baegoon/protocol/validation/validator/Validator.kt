package com.baegoon.protocol.validation.validator

import com.google.protobuf.Descriptors

interface Validator {
    fun <T> validate(fieldName: String, fieldValue: T, rule: Map.Entry<Descriptors.FieldDescriptor, Any>): Boolean
}
