package com.baegoon.app.api.controller

import com.baegoon.app.api.aop.proxy.PerformanceService
import com.baegoon.app.api.aop.spring.PrefixService
import com.baegoon.domain.main.domain.item.laptop.Laptop
import com.baegoon.domain.main.domain.item.laptop.LaptopRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController(
    private val laptopRepository: LaptopRepository,
    private val performanceService: PerformanceService,
    private val prefixService: PrefixService
) {

    @GetMapping
    fun hello(@RequestParam name: String): ResponseEntity<String> {
        this.laptopRepository.save(
            Laptop(
                name = "맥북",
                price = 1500000,
                ports = 4,
                screenSize = 15
            )
        )
        return ResponseEntity.ok("Hello $name !!")
    }

    @GetMapping("/test")
    fun test(): ResponseEntity<*> {
        performanceService.test()
        println(prefixService.printMessage())

        return ResponseEntity.ok("ok")
    }
}
