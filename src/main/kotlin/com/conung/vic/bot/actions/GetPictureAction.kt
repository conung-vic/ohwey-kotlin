package com.conung.vic.bot.actions

import com.conung.vic.bot.client.TelegramClient
import com.conung.vic.bot.client.beans.Message
import java.util.HashMap

class GetPictureAction: Action {

    override fun execute(command: Message) {
        val msg: MutableMap<String, Any> = HashMap()
        msg["chat_id"] = command.chat.id
        msg["reply_to_message_id"] = command.messageId
        msg["document"] = "CgADAgADLQADCYuQSpnarupPA-cqAg" // гопники в лифте
        //msg["document"] = "CgADAgADzgAD4tEoSKfmeIynm89IAg" // гопники сидят
        TelegramClient.sendDocument(msg)
    }

    override fun getName(): String = "picture"

    override fun getDescription(): String = "random pic"

    override fun canBeCalledByUser(): Boolean = true
}