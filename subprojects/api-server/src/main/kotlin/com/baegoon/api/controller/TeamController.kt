package com.baegoon.api.controller

import com.baegoon.api.dto.TeamRequest
import com.baegoon.domain.team.Team
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
@RequestMapping("/teams")
class TeamController(
    private val teamRepository: TeamRepository
) {

    @GetMapping
    fun index(): ResponseEntity<*> {
        val list = this.teamRepository.findAll()
        return ResponseEntity.ok(list)
    }

    @GetMapping("/{id}")
    fun inquire(@PathVariable id: Long): ResponseEntity<*> {
        val team = this.teamRepository.findById(id).orElseThrow {
            EntityNotFoundException("존재하지 않는 팀 입니다.")
        }

        return ResponseEntity.ok(team)
    }

    @PostMapping
    fun create(@RequestBody request: TeamRequest): ResponseEntity<*> {
        val team = this.teamRepository.save(
            Team(request.teamName)
        )

        val selfLink = linkTo(javaClass).slash(team.id).toUri()

        return ResponseEntity.created(selfLink).body(team)
    }
}
