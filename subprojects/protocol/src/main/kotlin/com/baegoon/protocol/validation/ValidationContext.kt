package com.baegoon.protocol.validation

import com.baegoon.protocol.rule.Rule
import com.baegoon.protocol.validation.validator.EmailValidator
import com.baegoon.protocol.validation.validator.MaxLengthValidator
import com.baegoon.protocol.validation.validator.MaxValidator
import com.baegoon.protocol.validation.validator.MinLengthValidator
import com.baegoon.protocol.validation.validator.MinValidator
import com.baegoon.protocol.validation.validator.NumericValidator
import com.baegoon.protocol.validation.validator.PhoneValidator
import com.baegoon.protocol.validation.validator.RegexValidator
import com.baegoon.protocol.validation.validator.Validator
import com.google.protobuf.Descriptors

/**
 * validator 와 에러메시지를 관리하는 object
 */
object ValidationContext {

    private const val CONDITION_TAG = "{condition}"

    private val validators = mapOf(
        Rule.max.descriptor to ValidationHolder(
            validator = MaxValidator(),
            errorMessage = "$CONDITION_TAG 보다 큰 숫자는 입력할 수 없습니다."
        ),
        Rule.min.descriptor to ValidationHolder(
            validator = MinValidator(),
            errorMessage = "$CONDITION_TAG 보다 작은 숫자는 입력할 수 없습니다."
        ),
        Rule.maxLength.descriptor to ValidationHolder(
            validator = MaxLengthValidator(),
            errorMessage = "$CONDITION_TAG 이하로 입력해 주시기 바랍니다."
        ),
        Rule.minLength.descriptor to ValidationHolder(
            validator = MinLengthValidator(),
            errorMessage = "$CONDITION_TAG 이상으로 입력해 주시기 바랍니다."
        ),
        Rule.regex.descriptor to ValidationHolder(
            validator = RegexValidator(),
            errorMessage = "올바른 형식이 아닙니다."
        ),
        Rule.email.descriptor to ValidationHolder(
            validator = EmailValidator(),
            errorMessage = "정확한 이메일 형식이 아닙니다."
        ),
        Rule.phone.descriptor to ValidationHolder(
            validator = PhoneValidator(),
            errorMessage = "정확한 휴대전화 번호 형식이 아닙니다."
        ),
        Rule.numeric.descriptor to ValidationHolder(
            validator = NumericValidator(),
            errorMessage = "숫자만 입력 가능합니다."
        )
    )

    fun getValidator(key: Descriptors.FieldDescriptor): Validator? {
        return validators[key]?.validator
    }

    fun getErrorMessage(rule: Map.Entry<Descriptors.FieldDescriptor, Any>): String? {
        return validators[rule.key]?.errorMessage?.replace(CONDITION_TAG, rule.value.toString())
    }
}
