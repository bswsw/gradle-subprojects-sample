package com.baegoon.domain.team

import com.baegoon.domain.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Team : BaseEntity() {

    @Column(name = "team_name", nullable = false, unique = true)
    var name: String = ""
}
