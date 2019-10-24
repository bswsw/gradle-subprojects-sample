package com.baegoon.domain.member

import com.baegoon.domain.team.Team
import com.baegoon.domain.team.TeamRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MemberService(
    private val teamRepository: TeamRepository,
    private val memberRepository: MemberRepository
) {

    fun register(teamName: String, memberType: MemberType, name: String, email: String, backNumber: Int, birthDate: LocalDate): Member {
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
