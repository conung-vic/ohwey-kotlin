package com.conung.vic.bot.actions

interface Action {
    fun execute(command: Map<*, *>)
    fun getName(): String
    fun getDescription(): String
    fun canBeCalledByUser(): Boolean
}