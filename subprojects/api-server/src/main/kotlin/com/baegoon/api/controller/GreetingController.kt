package com.baegoon.api.controller

import com.baegoon.proto.greeting.GreetingGrpc
import com.baegoon.proto.greeting.GreetingRequest
import io.grpc.ManagedChannelBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/greeting")
class GreetingController {

    private val channel = ManagedChannelBuilder
        .forAddress("localhost", 1234)
        .usePlaintext()
        .build()

    @GetMapping
    fun greeting(@RequestParam name: String): ResponseEntity<*> {
        val request = GreetingRequest.newBuilder()
            .setName(name)
            .build()

        val response = GreetingGrpc
            .newBlockingStub(this.channel)
            .greeting(request)

        return ResponseEntity.ok(response.hello)
    }
}
