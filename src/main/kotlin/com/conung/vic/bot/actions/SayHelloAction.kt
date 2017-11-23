package com.conung.vic.bot.actions

import com.conung.vic.bot.client.beans.Message
import com.conung.vic.bot.client.beans.User
import org.slf4j.LoggerFactory
import java.util.*

class SayHelloAction: Action {
    val log = LoggerFactory.getLogger(SayHelloAction::class.java)

    override fun execute(command: Message) {
        log.debug(command.toString())
        val newbies: List<User> = command.newChatMembers ?: LinkedList()
        newbies.forEach {
            newbie -> log.debug("New member with id ${newbie.id}")
        }
    }

    override fun getName(): String = "SayHello"

    override fun getDescription(): String = "Sends to user some greetings message"

    override fun canBeCalledByUser(): Boolean = false
}