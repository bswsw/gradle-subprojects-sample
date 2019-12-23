package com.baegoon.app.grpc.service

import com.baegoon.protocol.proto.TeamCreationRequest
import com.baegoon.protocol.proto.TeamCreationResponse
import com.baegoon.protocol.proto.TeamGrpc
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class TeamGRpcService : TeamGrpc.TeamImplBase() {

    override fun createTeam(request: TeamCreationRequest, responseObserver: StreamObserver<TeamCreationResponse>) {
        val response = TeamCreationResponse.newBuilder().let {
            it.teamId = 1
            it.name = request.name
            it.build()
        }

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}
