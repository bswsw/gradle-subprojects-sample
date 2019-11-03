package com.baegoon.app.grpc.service

import com.baegoon.domain.main.domain.member.MemberRegisterService
import com.baegoon.domain.main.member.MemberType
import com.baegoon.proto.member.MemberGrpc
import com.baegoon.proto.member.MemberRegisterRequestProto
import com.baegoon.proto.member.MemberRegisterResponseProto
import com.baegoon.protocol.extension.toLocalDate
import com.baegoon.protocol.extension.toTimestampProto
import com.baegoon.protocol.interceptor.ProtobufValidationInterceptor
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService(interceptors = [ProtobufValidationInterceptor::class])
class MemberService(
    private val memberRegisterService: MemberRegisterService
) : MemberGrpc.MemberImplBase() {

    override fun register(
        request: MemberRegisterRequestProto,
        responseObserver: StreamObserver<MemberRegisterResponseProto>
    ) {
        val member = this.memberRegisterService.register(
            teamName = request.teamName,
            memberType = MemberType.valueOf(request.memberType.name),
            name = request.name,
            email = request.email,
            backNumber = request.backNumber,
            birthDate = request.birthDate.toLocalDate()
        )

        val response = MemberRegisterResponseProto.newBuilder()
            .setMemberId(member.id!!)
            .setName(member.name)
            .setBackNumber(member.backNumber)
            .setBirthDate(member.birthDate.toTimestampProto())
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}
