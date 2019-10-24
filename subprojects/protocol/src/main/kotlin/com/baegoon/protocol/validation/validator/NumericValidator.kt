package com.baegoon.protocol.validation.validator

import com.google.protobuf.Descriptors
import java.lang.Double.parseDouble

class NumericValidator : Validator {

    override fun <T> validate(
        fieldName: String,
        fieldValue: T,
        rule: Map.Entry<Descriptors.FieldDescriptor, Any>
    ): Boolean {
        return try {
            parseDouble(fieldValue.toString())
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}
