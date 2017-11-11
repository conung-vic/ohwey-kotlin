package com.conung.vic.bot

import com.conung.vic.bot.client.TelegramClient
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    while (true) {
        val msg = TelegramClient.getUpdates()
        msg.forEach { m ->
            if (m is Map<*, *>) {
                if ((m["text"] as String).startsWith("/")) {
                    ActionExecutor.executeCommand(m)
                } else {
                    ActionExecutor.countMessage(m)
                }
            }

        }
        TimeUnit.SECONDS.sleep(2)
    }
}