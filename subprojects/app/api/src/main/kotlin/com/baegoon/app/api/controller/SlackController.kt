package com.baegoon.app.api.controller

import com.baegoon.app.api.properties.SlackHookUrlProperties
import com.baegoon.client.slack.SlackClient
import com.baegoon.client.slack.message.BlockDivider
import com.baegoon.client.slack.message.BlockMessage
import com.baegoon.client.slack.message.MarkDownBlock
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/slacks")
class SlackController(
    private val slackHookUrlProperties: SlackHookUrlProperties
) {

    @GetMapping
    fun send(@RequestParam message: String): ResponseEntity<*> {
        val client = SlackClient(this.slackHookUrlProperties.semicolon)

        val response = client.send(
            BlockMessage(
                text = message,
                channel = "#general",
                username = "baegoonyyy",
                iconEmoji = ":kissing_cat:",
                blocks = arrayOf(
                    MarkDownBlock("A message *with some bold text* and _some italicized text_."),
                    BlockDivider(),
                    MarkDownBlock("A message *with some bold text* and _some italicized text_.")
                )
            )
        )

        return ResponseEntity.ok("${response.statusCode} :: ${response.body} :: ${response.headers}")
    }
}
