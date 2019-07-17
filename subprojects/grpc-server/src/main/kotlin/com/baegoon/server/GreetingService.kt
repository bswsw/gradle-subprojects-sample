package com.baegoon.server

import com.baegoon.proto.greeting.GreetingGrpc
import com.baegoon.proto.greeting.GreetingRequest
import com.baegoon.proto.greeting.GreetingResponse
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class GreetingService : GreetingGrpc.GreetingImplBase() {

    override fun greeting(request: GreetingRequest, responseObserver: StreamObserver<GreetingResponse>) {
        val response = GreetingResponse.newBuilder()
            .setHello("${request.name}님 안녕하세요!")
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}
