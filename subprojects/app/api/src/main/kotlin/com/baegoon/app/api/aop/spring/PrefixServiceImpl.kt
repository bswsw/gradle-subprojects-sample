package com.baegoon.app.api.aop.spring

import org.springframework.stereotype.Service

@Service
class PrefixServiceImpl : PrefixService {

    @MyPrefix("sangwoo")
    override fun printMessage(): Any {
        return "my message!!!"
    }
}
