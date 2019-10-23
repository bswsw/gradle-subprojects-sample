package com.baegoon.grpc.service

import com.baegoon.proto.member.MemberGrpc
import com.baegoon.proto.member.MemberRegisterRequestProto
import com.baegoon.proto.member.MemberRegisterResponseProto
import com.baegoon.protocol.interceptor.ProtobufValidationInterceptor
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService(interceptors = [ProtobufValidationInterceptor::class])
class MemberService : MemberGrpc.MemberImplBase() {

    override fun register(
        request: MemberRegisterRequestProto,
        responseObserver: StreamObserver<MemberRegisterResponseProto>
    ) {
        val response = MemberRegisterResponseProto.newBuilder()
            .setMemberId((100..200L).random())
            .setName(request.name)
            .setBackNumber(request.backNumber)
            .setBirthDate(request.birthDate)
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}
