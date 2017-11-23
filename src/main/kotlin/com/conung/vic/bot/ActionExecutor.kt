package com.conung.vic.bot

import com.conung.vic.bot.client.beans.Message
import com.conung.vic.bot.client.beans.Update
import com.conung.vic.bot.client.beans.User
import com.conung.vic.bot.finance.Accounts
import org.slf4j.LoggerFactory
import java.util.*

object ActionExecutor {
    fun parseMessage(msg: Update) {
        val message = msg.message
        if (message != null) {
            if (message.newChatMembers != null) {
                sayHelloToNewbies(message)
            } else if (message.text != null) {
                val txt: String = message.text ?: ""
                if (txt.startsWith("/")) {
                    executeCommand(message)
                } else {
                    countMessage(message)
                }
            }
        }
    }

    private fun sayHelloToNewbies(msg: Message) {
        if (msg.newChatMembers != null) {
            ActionRegistrator.get("SayHello")?.execute(msg)
        }
    }


    private fun executeCommand(msg: Message) {
        var txt = msg.text ?: " "
        txt = txt.substring(1, txt.length)
        val parts = txt.split(' ', '@')
        val cmdName = parts[0]
        val action = ActionRegistrator.get(cmdName)
        action?.execute(msg)
    }

    private fun countMessage(msg: Message) {
        val userId = msg.from?.id ?: 0
        val chatId = msg.chat.id
        val len = msg.text?.length ?: 0

        val newThread = Thread({
            val log = LoggerFactory.getLogger("Balance Writer")
            log.debug("Change balance for user $userId in chatroom $chatId, delta = $len")
            Accounts().changeBalance(chatId, userId, len)
            log.debug("Balance for user $userId in chatroom $chatId successfully changed")
        })
        newThread.name = "Balance change for $chatId/$userId"
        newThread.start()
    }
}