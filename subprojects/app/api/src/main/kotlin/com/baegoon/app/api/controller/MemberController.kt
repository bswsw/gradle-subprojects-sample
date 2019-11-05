package com.baegoon.app.api.controller

import com.baegoon.domain.main.domain.member.MemberGender
import com.baegoon.domain.main.domain.member.MemberRepository
import com.baegoon.domain.main.domain.member.MemberType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(
    private val memberRepository: MemberRepository
) {

    @GetMapping
    fun getMembers(): ResponseEntity<*> {
        this.memberRepository.findAllByMemberType(MemberType.BASIC)
        this.memberRepository.findAllByGender(MemberGender.MALE)
        this.memberRepository.findAllBasic()

        return ResponseEntity.ok("OK")
    }
}
