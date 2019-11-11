package com.baegoon.client.slack.message

class BlockMessage(
    override val text: String,
    override val channel: String,
    override val username: String,
    override val iconEmoji: String,
    val blocks: Array<Block>
) : Message
