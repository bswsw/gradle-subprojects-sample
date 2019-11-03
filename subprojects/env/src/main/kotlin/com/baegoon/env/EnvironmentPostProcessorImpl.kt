package com.baegoon.env

import org.springframework.boot.SpringApplication
import org.springframework.boot.context.config.ConfigFileApplicationListener
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent
import org.springframework.boot.context.event.ApplicationPreparedEvent
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.boot.env.YamlPropertySourceLoader
import org.springframework.boot.logging.DeferredLog
import org.springframework.context.ApplicationEvent
import org.springframework.context.event.SmartApplicationListener
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.PropertySource
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.stereotype.Component

@Component
class EnvironmentPostProcessorImpl : EnvironmentPostProcessor, SmartApplicationListener {

    private val log = DeferredLog()
    private val ymlLoader = YamlPropertySourceLoader()

    override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
        val loader = PathMatchingResourcePatternResolver()
        val resources = loader.getResources("classpath*:application-*.yml")

        resources.forEachIndexed { idx, it ->
            this.loadYml(it)?.let {
                this.addProperties(environment, it)
                println("Loading Property[$idx] : ${it.name}")
                log.info("Loading Property[$idx] : ${it.name}")
            }
        }
    }

    override fun onApplicationEvent(event: ApplicationEvent) {
        if (event is ApplicationPreparedEvent) {
            log.switchTo(EnvironmentPostProcessorImpl::class.java)
        }
    }

    override fun supportsEventType(eventType: Class<out ApplicationEvent>): Boolean {
        return ApplicationEnvironmentPreparedEvent::class.java.isAssignableFrom(eventType) ||
            ApplicationPreparedEvent::class.java.isAssignableFrom(eventType)
    }

    private fun loadYml(resource: Resource): PropertySource<out Any>? {
        return this.ymlLoader.load(resource.filename, resource)[0]
    }

    private fun addProperties(environment: ConfigurableEnvironment, propertySource: PropertySource<out Any>) {
        environment.propertySources.addLast(propertySource)
    }

    override fun getOrder(): Int {
        return ConfigFileApplicationListener.DEFAULT_ORDER - 1
    }
}
