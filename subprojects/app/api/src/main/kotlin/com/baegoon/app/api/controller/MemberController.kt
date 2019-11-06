package com.baegoon.app.api.controller

import com.baegoon.domain.core.dto.MemberRegistration
import com.baegoon.domain.core.service.MemberRegisterService
import com.baegoon.domain.main.domain.member.MemberType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/members")
class MemberController(
    private val memberRegisterService: MemberRegisterService
) {

    @GetMapping
    fun getMembers(): ResponseEntity<*> {
        return ResponseEntity.ok("OK")
    }

    @GetMapping("/join")
    fun join(): ResponseEntity<*> {
        val registration = MemberRegistration(
            teamName = "팀1",
            memberType = MemberType.BASIC,
            name = "이름",
            email = "이메일",
            backNumber = 100,
            birthDate = LocalDate.of(1999, 11, 30)
        )
        val newMember = this.memberRegisterService.register(registration)

        return ResponseEntity.ok(newMember)
    }
}
