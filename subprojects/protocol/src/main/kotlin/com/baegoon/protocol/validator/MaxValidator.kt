package com.baegoon.protocol.validator

import com.google.protobuf.Descriptors

class MaxValidator : Validator {

    override fun <T> validate(fieldName: String, fieldValue: T, rule: Map.Entry<Descriptors.FieldDescriptor, Any>): Boolean {
        val ruleValue = rule.value.toString()

        val isValidated = when (fieldValue) {
            is Long -> fieldValue <= ruleValue.toLong()
            is Int -> fieldValue <= ruleValue.toInt()
            is Double -> fieldValue <= ruleValue.toDouble()
            is Float -> fieldValue <= ruleValue.toFloat()
            else -> false
        }

        return this.check(
            isValidated = isValidated,
            fieldName = fieldName,
            errorMessage = "너무 크다."
        )
    }
}
