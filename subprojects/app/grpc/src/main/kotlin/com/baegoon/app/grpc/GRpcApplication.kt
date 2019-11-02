package com.baegoon.app.grpc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.baegoon"])
class GRpcApplication

fun main(args: Array<String>) {
    runApplication<GRpcApplication>(*args)
}
