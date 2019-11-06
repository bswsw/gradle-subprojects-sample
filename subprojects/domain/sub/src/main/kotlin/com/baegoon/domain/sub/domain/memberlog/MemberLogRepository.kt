package com.baegoon.domain.sub.domain.memberlog

import org.springframework.data.jpa.repository.JpaRepository

interface MemberLogRepository : JpaRepository<MemberLog, Long>
