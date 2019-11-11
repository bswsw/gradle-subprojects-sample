package com.baegoon.client.slack.message

class MarkDownBlock(text: String) : Block() {

    val text = MarkDown(text = text)

    data class MarkDown(val text: String) {
        val type: String = "mrkdwn"
    }
}
