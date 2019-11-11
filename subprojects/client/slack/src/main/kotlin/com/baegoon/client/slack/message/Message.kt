package com.baegoon.client.slack.message

import com.fasterxml.jackson.annotation.JsonProperty

interface Message {
    val text: String
    val channel: String
    val username: String
    @get:JsonProperty("icon_emoji")
    val iconEmoji: String
}
