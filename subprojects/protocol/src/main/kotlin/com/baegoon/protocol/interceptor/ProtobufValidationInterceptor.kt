package com.baegoon.protocol.interceptor

import com.baegoon.protocol.exception.ProtoValidationException
import com.baegoon.protocol.rule.Rule
import com.baegoon.protocol.validator.MaxLengthValidator
import com.baegoon.protocol.validator.MaxValidator
import com.baegoon.protocol.validator.MinLengthValidator
import com.google.protobuf.Descriptors
import com.google.protobuf.GeneratedMessageV3
import io.grpc.ForwardingServerCallListener
import io.grpc.Metadata
import io.grpc.ServerCall
import io.grpc.ServerCallHandler
import io.grpc.ServerInterceptor
import io.grpc.Status

class ProtobufValidationInterceptor : ServerInterceptor {

    private val validationRules = mapOf(
        Rule.maxLength.descriptor to MaxLengthValidator(),
        Rule.minLength.descriptor to MinLengthValidator(),
        Rule.max.descriptor to MaxValidator()
    )

    override fun <ReqT : Any, RespT : Any> interceptCall(
        call: ServerCall<ReqT, RespT>,
        headers: Metadata,
        next: ServerCallHandler<ReqT, RespT>
    ): ServerCall.Listener<ReqT> {
        val listener = next.startCall(call, headers)

        return object : ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(listener) {
            override fun onMessage(message: ReqT) {
                message as GeneratedMessageV3

                try {
                    validateMessage(message)
                    super.onMessage(message)
                } catch (e: ProtoValidationException) {
                    call.close(Status.INVALID_ARGUMENT.withDescription(e.message), Metadata())
                } catch (e: Exception) {
                    call.close(Status.INTERNAL.withDescription(e.message), Metadata())
                }
            }
        }
    }

    @Throws(ProtoValidationException::class)
    private fun validateMessage(message: GeneratedMessageV3) {
        message.descriptorForType.fields.forEach { fieldDescriptor ->
            val fieldValue = message.getField(fieldDescriptor)

            this.validateField(fieldDescriptor.name, fieldValue, fieldDescriptor.options.allFields)

            if (fieldValue is GeneratedMessageV3) {
                validateMessage(fieldValue)
                return@forEach
            }
        }
    }

    @Throws(ProtoValidationException::class)
    private fun validateField(fieldName: String, fieldValue: Any, fieldRules: Map<Descriptors.FieldDescriptor, Any>) {
        fieldRules.entries.forEach {
            validationRules[it.key]?.validate(fieldName, fieldValue, it)
        }
    }
}
