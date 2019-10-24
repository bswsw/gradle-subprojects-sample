package com.baegoon.domain.member

import com.baegoon.domain.base.BaseEntity
import com.baegoon.domain.team.Team
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.ManyToOne

@Entity
class Member(
    @ManyToOne
    var team: Team,
    @Enumerated(EnumType.STRING)
    var memberType: MemberType,
    @Column(name = "member_name", nullable = false)
    var name: String,
    var email: String,
    var backNumber: Int,
    var birthDate: LocalDate
) : BaseEntity()
