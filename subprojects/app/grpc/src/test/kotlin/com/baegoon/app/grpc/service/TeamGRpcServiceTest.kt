package com.baegoon.app.grpc.service

import com.baegoon.protocol.proto.TeamCreationRequest
import com.baegoon.protocol.proto.TeamGrpc
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import io.grpc.testing.GrpcCleanupRule
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
internal class TeamGRpcServiceTest {

    @get:Rule
    val grpcCleanupRule = GrpcCleanupRule()

    @Test
    fun test_CreateTeam() {
        val request = TeamCreationRequest.newBuilder().let {
            it.name = "배군"
            it.build()
        }

        val response = getBlockingStub().createTeam(request)

        Assertions.assertEquals(response.name, request.name)
    }

    private fun getBlockingStub(): TeamGrpc.TeamBlockingStub {
        val serverName = InProcessServerBuilder.generateName()

        this.grpcCleanupRule.register(
            InProcessServerBuilder
                .forName(serverName)
                .directExecutor()
                .addService(TeamGRpcService())
                .build()
                .start()
        )

        return TeamGrpc.newBlockingStub(
            this.grpcCleanupRule.register(
                InProcessChannelBuilder
                    .forName(serverName)
                    .directExecutor()
                    .build()
            )
        )
    }
}
