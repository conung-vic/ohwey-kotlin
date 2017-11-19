package com.conung.vic.bot.actions

import org.slf4j.LoggerFactory

class SayHelloAction: Action {
    val log = LoggerFactory.getLogger(SayHelloAction::class.java)

    override fun execute(command: Map<*, *>) {
        log.debug(command.toString())
        val newbie = Thread({

        })
        newbie.name = "New memner with id ${command["id"]}"
        newbie.start()
    }

    override fun getName(): String = "SayHello"

    override fun getDescription(): String = "Sends to user some greetings message"

    override fun canBeCalledByUser(): Boolean = false
}