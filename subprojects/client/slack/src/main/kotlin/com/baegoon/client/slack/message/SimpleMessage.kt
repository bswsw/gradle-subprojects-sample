package com.baegoon.client.slack.message

class SimpleMessage(
    override val text: String,
    val channel: String,
    val username: String
) : Message
