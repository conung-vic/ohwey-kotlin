package com.conung.vic.bot

import org.slf4j.LoggerFactory

object Helper {
    private val log = LoggerFactory.getLogger(Helper::class.java)

    fun getChatId(msg: Map<*, *>): Int {
        val chat = msg["chat"]
        if (chat !is Map<*, *>) {
            log.warn("This shoudn't happend.")
            return -1
        }
        return chat["id"] as Int? ?: -1
    }

}