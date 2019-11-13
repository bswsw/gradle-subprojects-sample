package com.baegoon.app.api.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("slack.hook-url")
class SlackHookUrlProperties {

    lateinit var semicolon: String
}
