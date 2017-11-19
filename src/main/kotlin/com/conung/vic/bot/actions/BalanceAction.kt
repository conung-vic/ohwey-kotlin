package com.conung.vic.bot.actions

import com.conung.vic.bot.Helper
import com.conung.vic.bot.client.TelegramClient
import com.conung.vic.bot.finance.Accounts
import com.sun.org.apache.xpath.internal.operations.Bool
import org.slf4j.LoggerFactory
import java.util.*


class BalanceAction : Action {
    private val log = LoggerFactory.getLogger(BalanceAction::class.java)
    private val BALANCE = "balance"

    override fun execute(command: Map<*, *>) {
        log.debug("Command $BALANCE executed")
        val chatId = Helper.getChatId(command)
        val userId = Helper.getUserId(command)
        val msgId = command["message_id"] as Int? ?: -1

        val newThread = Thread({
            val log = LoggerFactory.getLogger("Balance Reader")
            log.debug("Requested balance for user $userId in chatroom $chatId")
            val msg: MutableMap<String, Any> = HashMap()
            val balance = Accounts().getBalance(chatId, userId)
            msg["chat_id"] = chatId
            msg["text"] = "Your balance is $balance"
            msg["reply_to_message_id"] = msgId
            log.debug("Balance for user $userId in chatroom $chatId = $balance")
            TelegramClient.sendMessage(msg)
        })
        newThread.name = "Balance for $chatId/$userId"
        newThread.start()
    }

    override fun getName(): String {
        return BALANCE
    }

    override fun getDescription(): String {
        return "shows balance of user"
    }

    override fun canBeCalledByUser(): Boolean = true
}