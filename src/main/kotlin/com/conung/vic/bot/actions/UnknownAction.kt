package com.conung.vic.bot.actions

import com.conung.vic.bot.ActionRegistrator
import com.conung.vic.bot.client.TelegramClient
import com.conung.vic.bot.client.beans.Message
import com.google.common.base.Joiner
import org.slf4j.LoggerFactory


class UnknownAction : Action {
    private val log = LoggerFactory.getLogger(UnknownAction::class.java)

    override fun execute(command: Message) {
        log.debug("Called unknown command: ${command.text}")
        val msg: MutableMap<String, Any> = HashMap()
        msg["chat_id"] = command.chat.id
        msg["text"] = "Known commands are:\n" + Joiner.on("\n").join(ActionRegistrator.getNames())
        msg["reply_to_message_id"] = command.messageId
        TelegramClient.sendMessage(msg)
    }

    override fun getName(): String = "unknown_command"

    override fun getDescription(): String = ""

    override fun canBeCalledByUser(): Boolean = false
}