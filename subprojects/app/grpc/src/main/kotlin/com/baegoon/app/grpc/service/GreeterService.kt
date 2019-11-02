package com.baegoon.app.grpc.service

import com.baegoon.proto.greeter.GreeterGrpc
import com.baegoon.proto.greeter.GreeterRequest
import com.baegoon.proto.greeter.GreeterResponse
import io.grpc.Status
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class GreeterService : GreeterGrpc.GreeterImplBase() {

    override fun greeting(request: GreeterRequest, responseObserver: StreamObserver<GreeterResponse>) {
        val name = request.name

        if (name.isBlank()) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("이름을 입력해주세요.").asException())
        }

        val response = GreeterResponse.newBuilder()
            .setHello("${request.name} 님 안녕하세요!!")
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}
