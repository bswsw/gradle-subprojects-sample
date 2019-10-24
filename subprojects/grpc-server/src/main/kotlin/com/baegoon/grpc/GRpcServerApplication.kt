package com.baegoon.grpc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.baegoon"])
class GRpcServerApplication

fun main(args: Array<String>) {
    runApplication<GRpcServerApplication>(*args)
}
