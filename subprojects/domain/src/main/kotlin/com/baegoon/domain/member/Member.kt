package com.baegoon.domain.member

import com.baegoon.domain.base.BaseEntity
import com.baegoon.domain.team.Team
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class Member : BaseEntity() {

    @ManyToOne
    var team: Team = Team()

    @Column(name = "member_name", nullable = false)
    var name: String = ""
}
