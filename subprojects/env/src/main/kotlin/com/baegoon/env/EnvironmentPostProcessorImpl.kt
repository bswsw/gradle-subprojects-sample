package com.baegoon.env

import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.boot.env.YamlPropertySourceLoader
import org.springframework.boot.logging.DeferredLog
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.PropertySource
import org.springframework.core.io.ClassPathResource

class EnvironmentPostProcessorImpl : EnvironmentPostProcessor, ApplicationListener<ApplicationEvent> {

    private val log = DeferredLog()

    private val moduleNames = arrayOf("domain")

    private val ymlLoader = YamlPropertySourceLoader()

    override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
        this.moduleNames.forEach {
            this.loadYml(it)?.let { propertySource ->
                log.info("propertySource : $propertySource")
                environment.propertySources.addLast(propertySource)
            }
        }
    }

    override fun onApplicationEvent(event: ApplicationEvent) {
        log.switchTo(EnvironmentPostProcessorImpl::class.java)
    }

    private fun loadYml(moduleName: String): PropertySource<out Any>? {
        val resource = ClassPathResource("application-$moduleName.yml")
        return this.ymlLoader.load(resource.filename, resource)[0]
    }
}
