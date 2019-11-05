package com.baegoon.domain.main.domain.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>, MemberRepositoryDsl {
    fun findAllByMemberType(memberType: MemberType): List<Member>
    fun findAllByGender(memberGender: MemberGender): List<Member>
}
