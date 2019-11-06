package com.baegoon.domain.sub.domain.memberlog

import com.baegoon.domain.common.BaseEntity
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class MemberLog(
    @Enumerated(EnumType.STRING)
    val logType: MemberLogType,
    val memberId: Long
) : BaseEntity()
