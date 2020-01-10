package com.baegoon.app.api.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("slack.hook-url")
@ConstructorBinding
class SlackHookUrlProperties(
    val semicolon: String
)
