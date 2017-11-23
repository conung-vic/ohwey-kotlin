package com.conung.vic.bot.actions

import com.conung.vic.bot.client.beans.Message

interface Action {
    fun execute(command: Message)
    fun getName(): String
    fun getDescription(): String
    fun canBeCalledByUser(): Boolean
}