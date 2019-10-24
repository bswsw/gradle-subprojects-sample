package com.baegoon.protocol.validation.validator

import com.google.protobuf.Descriptors

class EmailValidator : Validator {

    companion object {
        private const val EMAIL_REGEX =
            "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$"
    }

    override fun <T> validate(
        fieldName: String,
        fieldValue: T,
        rule: Map.Entry<Descriptors.FieldDescriptor, Any>
    ): Boolean {
        return EMAIL_REGEX.toRegex().matches(fieldValue.toString())
    }
}
