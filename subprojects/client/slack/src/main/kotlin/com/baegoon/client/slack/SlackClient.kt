package com.baegoon.client.slack

import com.baegoon.client.slack.message.Message
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

class SlackClient(
    private val webHookUrl: String
) {

    private val restTemplate = RestTemplate()

    fun send(message: Message): ResponseEntity<String> {
        return this.restTemplate.postForEntity(
            this.webHookUrl,
            message,
            String::class.java
        )
    }
}
