package com.baegoon.protocol.validation.validator

import com.google.protobuf.Descriptors

class MaxValidator : Validator {

    override fun <T> validate(
        fieldName: String,
        fieldValue: T,
        rule: Map.Entry<Descriptors.FieldDescriptor, Any>
    ): Boolean {
        val ruleValue = rule.value.toString()

        return when (fieldValue) {
            is Long -> fieldValue <= ruleValue.toLong()
            is Int -> fieldValue <= ruleValue.toInt()
            is Double -> fieldValue <= ruleValue.toDouble()
            is Float -> fieldValue <= ruleValue.toFloat()
            else -> false
        }
    }
}
