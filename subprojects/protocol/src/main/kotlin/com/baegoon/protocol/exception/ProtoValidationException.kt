package com.baegoon.protocol.exception

class ProtoValidationException(fieldName: String, fieldValue: Any, message: String) :
    RuntimeException("$fieldName : $message ($fieldValue)")
