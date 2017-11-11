package com.conung.vic.bot.actions

import com.conung.vic.bot.ActionRegistrator
import com.conung.vic.bot.Helper
import com.conung.vic.bot.client.TelegramClient
import com.google.common.base.Joiner
import org.slf4j.LoggerFactory
import java.util.*


class HelpAction: Action {
    private val log = LoggerFactory.getLogger(HelpAction::class.java)
    private val HELP = "help"

    override fun execute(command: Map<*, *>) {
        log.debug("Command $HELP started")
        val names = ActionRegistrator.getNames()
        val help: MutableList<String> = LinkedList()
        names.forEach{name -> help.add(name + " : " + ActionRegistrator.get(name)?.getDescription())}
        val txt = Joiner.on("\n").join(help)

        val msg: MutableMap<String, Any> = HashMap()
        msg["chat_id"] = Helper.getChatId(command)
        msg["text"] = txt
        msg["reply_to_message_id"] = command["message_id"] ?: ""
        TelegramClient.sendMessage(msg)
    }

    override fun getName(): String {
        return HELP
    }

    override fun getDescription(): String {
        return "Shows list of command"
    }
}