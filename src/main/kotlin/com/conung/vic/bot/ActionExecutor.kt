package com.conung.vic.bot

import com.conung.vic.bot.finance.Accounts
import org.slf4j.LoggerFactory

object ActionExecutor {
    fun parseMessage(msg: Map<*, *>) {
        val newbies = msg["new_chat_members"]
        if (newbies != null) {
            sayHelloToNewbies(msg)
        } else if (msg["text"] != null) {
            val txt: String =  msg["text"] as String
            if (txt.startsWith("/")) {
                executeCommand(msg)
            } else {
                countMessage(msg)
            }
        }
    }

    private fun sayHelloToNewbies(msg: Map<*, *>) {
        val newbies: List<Map<String, *>> = msg["new_chat_members"] as List<Map<String, *>>
        newbies.forEach { newbie -> ActionRegistrator.get("SayHello")?.execute(newbie) }
    }


    private fun executeCommand(msg: Map<*, *>) {
        var txt = msg["text"] as String
        txt = txt.substring(1, txt.length)
        val parts = txt.split(' ', '@')
        val cmdName = parts[0]
        val action = ActionRegistrator.get(cmdName)
        action?.execute(msg)
    }

    private fun countMessage(msg: Map<*, *>) {
        val userId = Helper.getUserId(msg)
        val chatId = Helper.getChatId(msg)
        val len = (msg["text"] as String? )?.length ?: 0

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