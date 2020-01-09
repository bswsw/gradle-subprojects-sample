package com.baegoon.app.api.aop.spring

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Component
@Aspect
class PrefixAspect {

    @Around("@annotation(MyPrefix)")
    fun addPrefix(pjp: ProceedingJoinPoint): Any {
        return "myPrefix :: ${pjp.proceed()}"
    }
}
