package com.conung.vic.bot

object ActionExecutor {
    fun executeCommand(msg: Map<*, *>) {
        var txt = msg["text"] as String
        txt = txt.substring(1, txt.length)
        val parts = txt.split(' ', '@')
        val cmdName = parts[0]
        val action = ActionRegistrator.get(cmdName)
        action?.execute(msg)
    }

    fun countMessage(msg: Map<*, *>) {
        println("Simple text: ${msg["text"]}")
    }
}