package com.baegoon.domain.core.service

import com.baegoon.domain.core.config.ChainedTransactionManagerConfig
import com.baegoon.domain.core.dto.MemberRegistration
import com.baegoon.domain.main.domain.member.Member
import com.baegoon.domain.main.domain.member.MemberService
import com.baegoon.domain.sub.domain.memberlog.MemberLog
import com.baegoon.domain.sub.domain.memberlog.MemberLogRepository
import com.baegoon.domain.sub.domain.memberlog.MemberLogType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(ChainedTransactionManagerConfig.TRANSACTION_MANAGER_BEAN_NAME)
class MemberRegisterService(
    private val memberService: MemberService,
    private val memberLogRepository: MemberLogRepository
) {

    fun register(registration: MemberRegistration): Member {
        val member = this.memberService.register(
            teamName = registration.teamName,
            memberType = registration.memberType,
            name = registration.name,
            email = registration.email,
            backNumber = registration.backNumber,
            birthDate = registration.birthDate
        )

        this.memberLogRepository.save(
            MemberLog(
                logType = MemberLogType.JOIN,
                memberId = member.id!!
            )
        )

        return member
    }
}
