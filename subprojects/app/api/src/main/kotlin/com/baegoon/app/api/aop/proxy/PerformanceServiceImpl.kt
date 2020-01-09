package com.baegoon.app.api.aop.proxy

import org.springframework.stereotype.Service

@Service
class PerformanceServiceImpl : PerformanceService {

    override fun test(): String {
        Thread.sleep(1000)
        println("test complete!!")

        return "test complete!!"
    }

    override fun test2(): Int {
        Thread.sleep(2000)
        return 111
    }
}
