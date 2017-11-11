package com.conung.vic.bot.actions

import org.slf4j.LoggerFactory


class BalanceAction : Action {
    private val log = LoggerFactory.getLogger(BalanceAction::class.java)
    private val BALANCE = "balance"

    override fun execute(command: Map<*, *>) {
        log.debug("Command $BALANCE executed")
    }

    override fun getName(): String {
        return BALANCE
    }

    override fun getDescription(): String {
        return "shows balance of user"
    }
}