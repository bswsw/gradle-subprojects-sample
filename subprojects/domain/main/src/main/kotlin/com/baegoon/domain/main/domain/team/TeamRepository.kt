package com.baegoon.domain.main.domain.team

import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, Long> {
    fun findByName(name: String): Team?
}
