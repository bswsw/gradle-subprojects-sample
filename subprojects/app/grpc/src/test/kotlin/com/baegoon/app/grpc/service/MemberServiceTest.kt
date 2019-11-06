package com.baegoon.app.grpc.service

import com.baegoon.domain.main.domain.member.Member
import com.baegoon.domain.main.domain.member.MemberService
import com.baegoon.domain.main.domain.team.Team
import com.baegoon.domain.main.domain.member.MemberType
import com.baegoon.proto.member.Address
import com.baegoon.proto.member.MemberGrpc
import com.baegoon.proto.member.MemberRegisterRequestProto
import com.baegoon.proto.member.MemberTypeProto
import com.baegoon.protocol.extension.toTimestampProto
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import io.grpc.testing.GrpcCleanupRule
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate

@RunWith(SpringRunner::class)
@ActiveProfiles("test")
class MemberServiceTest {

    @get:Rule
    val grpcCleanupRule = GrpcCleanupRule()

    @Mock
    private lateinit var memberRegisterService: MemberService

    @Test
    fun `회원등록 테스트`() {
        // given
        val member = Member(
            team = Team("testTeam"),
            memberType = MemberType.BASIC,
            name = "testMember",
            email = "testMember@baegoon.com",
            backNumber = 10,
            birthDate = LocalDate.now()
        ).also {
            it.id = (1..10L).random()
        }

        `when`(
            this.memberRegisterService.register(
                teamName = member.team.name,
                memberType = member.memberType,
                name = member.name,
                email = member.email,
                backNumber = member.backNumber,
                birthDate = member.birthDate
            )
        ).thenReturn(member)

        // when
        val request = MemberRegisterRequestProto.newBuilder()
            .setTeamName(member.team.name)
            .setMemberType(MemberTypeProto.valueOf(member.memberType.name))
            .setName(member.name)
            .setEmail(member.email)
            .setPhone("01012341234")
            .setPassword("password")
            .setBackNumber(member.backNumber)
            .setBirthDate(member.birthDate.toTimestampProto())
            .setAddress(
                Address.newBuilder()
                    .setZipcode("12345")
                    .setAddress1("Korea")
                    .setAddress2("Seoul")
            )
            .addHobbies("Baseball")
            .addHobbies("Football")
            .build()

        val response = this.getBlockingStub().register(request)

        // then
        assertThat(response.memberId).isGreaterThan(0)
        assertThat(response.name).isEqualTo(member.name)
        assertThat(response.backNumber).isEqualTo(member.backNumber)
        assertThat(response.birthDate).isEqualTo(member.birthDate.toTimestampProto())
    }

    private fun getBlockingStub(): MemberGrpc.MemberBlockingStub {
        val serverName = InProcessServerBuilder.generateName()

        this.grpcCleanupRule.register(
            InProcessServerBuilder
                .forName(serverName)
                .directExecutor()
                .addService(MemberService(this.memberRegisterService))
                .build()
                .start()
        )

        return MemberGrpc.newBlockingStub(
            this.grpcCleanupRule.register(
                InProcessChannelBuilder
                    .forName(serverName)
                    .directExecutor()
                    .build()
            )
        )
    }
}
