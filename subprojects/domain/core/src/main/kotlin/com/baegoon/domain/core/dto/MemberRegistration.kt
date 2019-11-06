package com.baegoon.domain.core.dto

import com.baegoon.domain.main.domain.member.MemberType
import java.time.LocalDate

data class MemberRegistration(
    val teamName: String,
    val memberType: MemberType,
    val name: String,
    val email: String,
    val backNumber: Int,
    val birthDate: LocalDate
)
