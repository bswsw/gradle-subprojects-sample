package com.baegoon.app.api.aop.proxy

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Primary
@Service
class ProxyPerformanceService(
    private val performanceServiceImpl: PerformanceService
) : PerformanceService {

    override fun test(): String {
        val begin = System.currentTimeMillis()
        val result = performanceServiceImpl.test()

        println(System.currentTimeMillis() - begin)

        return result
    }

    override fun test2(): Int {
        val begin = System.currentTimeMillis()
        val result = performanceServiceImpl.test2()

        println(System.currentTimeMillis() - begin)

        return result
    }
}
