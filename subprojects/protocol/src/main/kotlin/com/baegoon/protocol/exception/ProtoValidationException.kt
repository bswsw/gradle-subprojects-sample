package com.baegoon.protocol.exception

class ProtoValidationException(fieldName: String, description: String) : RuntimeException("$fieldName : $description")
