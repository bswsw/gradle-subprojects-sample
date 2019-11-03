package com.baegoon.app.grpc.service

import com.baegoon.proto.greeter.GreeterGrpc
import com.baegoon.proto.greeter.GreeterRequest
import io.grpc.StatusRuntimeException
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import io.grpc.testing.GrpcCleanupRule
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ActiveProfiles("test")
class GreeterServiceTest {

    @get:Rule
    val grpcCleanupRule = GrpcCleanupRule()

    @Test
    fun `Greeter 테스트`() {
        val request = GreeterRequest.newBuilder()
            .setName("배군")
            .build()
        val response = this.getBlockingStub().greeting(request)

        assertThat(response.hello).contains(request.name)
    }

    @Test(expected = StatusRuntimeException::class)
    fun `greeter 공백 에러 테스트`() {
        val request = GreeterRequest.newBuilder()
            .setName(" ")
            .build()

        this.getBlockingStub().greeting(request)
    }

    private fun getBlockingStub(): GreeterGrpc.GreeterBlockingStub {
        val serverName = InProcessServerBuilder.generateName()

        this.grpcCleanupRule.register(
            InProcessServerBuilder
                .forName(serverName)
                .directExecutor()
                .addService(GreeterService())
                .build()
                .start()
        )

        return GreeterGrpc.newBlockingStub(
            this.grpcCleanupRule.register(
                InProcessChannelBuilder
                    .forName(serverName)
                    .directExecutor()
                    .build()
            )
        )
    }
}
