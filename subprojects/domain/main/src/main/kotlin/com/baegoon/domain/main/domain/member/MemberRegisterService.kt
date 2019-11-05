package com.baegoon.domain.main.domain.member

import com.baegoon.domain.main.domain.team.Team
import com.baegoon.domain.main.domain.team.TeamRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional(readOnly = true)
class MemberRegisterService(
    private val teamRepository: TeamRepository,
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun register(
        teamName: String,
        memberType: MemberType,
        name: String,
        email: String,
        backNumber: Int,
        birthDate: LocalDate
    ): Member {
        val team = this.teamRepository.findByName(teamName)
            ?: this.teamRepository.save(Team(teamName))

        return this.memberRepository.save(
            Member(
                team = team,
                memberType = memberType,
                name = name,
                email = email,
                backNumber = backNumber,
                birthDate = birthDate
            )
        )
    }
}
