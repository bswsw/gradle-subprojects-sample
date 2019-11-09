package com.baegoon.app.api.controller

import com.baegoon.client.slack.SlackClient
import com.baegoon.client.slack.WebHook
import com.baegoon.client.slack.message.SimpleMessage
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/slacks")
class SlackController {

    @GetMapping
    fun send(@RequestParam message: String): ResponseEntity<*> {
        val client = SlackClient(WebHook.SEMICOLON_STUDY)
        val response = client.send(SimpleMessage(text = message))

        return ResponseEntity.ok("${response.statusCode} :: ${response.body}")
    }
}
