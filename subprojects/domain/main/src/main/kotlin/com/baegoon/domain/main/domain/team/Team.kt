package com.baegoon.domain.main.domain.team

import com.baegoon.domain.common.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Team(
    @Column(name = "team_name", nullable = false, unique = true)
    var name: String = ""
) : BaseEntity()
