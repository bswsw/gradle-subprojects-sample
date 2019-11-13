package com.baegoon.app.api.config

import com.baegoon.app.api.properties.SlackHookUrlProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(SlackHookUrlProperties::class)
class AppConfig
