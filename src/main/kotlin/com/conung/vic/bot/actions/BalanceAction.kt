package com.conung.vic.bot.actions

import com.conung.vic.bot.client.TelegramClient
import com.conung.vic.bot.client.beans.Message
import com.conung.vic.bot.finance.Accounts
import org.slf4j.LoggerFactory
import java.util.*


class BalanceAction : Action {
    private val log = LoggerFactory.getLogger(BalanceAction::class.java)
    private val balance = "balance"

    override fun execute(command: Message) {
        log.debug("Command $balance executed")
        val chatId = command.chat.id
        val userId = command.from?.id ?: 0
        val msgId = command.messageId

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

    override fun getName(): String = balance

    override fun getDescription(): String = "shows balance of user"

    override fun canBeCalledByUser(): Boolean = true
}