package com.baegoon.api.controller

import com.baegoon.api.dto.MemberRequest
import com.baegoon.domain.member.Member
import com.baegoon.domain.member.MemberRepository
import com.baegoon.domain.team.TeamRepository
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/members")
class MemberController(
    private val teamRepository: TeamRepository,
    private val memberRepository: MemberRepository
) {

    @GetMapping
    fun index(): ResponseEntity<*> {
        val list = this.memberRepository.findAll()
        return ResponseEntity.ok(list)
    }

    @GetMapping("/{id}")
    fun inquire(@PathVariable("id") member: Member?): ResponseEntity<*> {
        return member?.let {
            ResponseEntity.ok(member)
        } ?: throw EntityNotFoundException("존재하지 않는 멤버 입니다.")
    }

    @PostMapping
    fun create(@RequestBody request: MemberRequest): ResponseEntity<*> {
        val team = this.teamRepository.findByName(request.teamName)
            ?: throw EntityNotFoundException("존재하지 않는 팀 입니다.")

        val member = this.memberRepository.save(
            Member(
                team = team,
                name = request.memberName
            )
        )

        val selfLink = linkTo(javaClass).slash(member.id).toUri()

        return ResponseEntity.created(selfLink).body(member)
    }
}
