package com.baegoon.protocol.validation.validator

import com.google.protobuf.Descriptors

class PhoneValidator : Validator {

    companion object {
        private const val PHONE_REGEX = "^01(([1-9]\\d{7})|[0-9]\\d{8})\$"
    }

    override fun <T> validate(fieldName: String, fieldValue: T, rule: Map.Entry<Descriptors.FieldDescriptor, Any>): Boolean {
        return PHONE_REGEX.toRegex().matches(fieldValue.toString())
    }
}
