package com.conung.vic.bot

import com.conung.vic.bot.client.TelegramClient
import com.conung.vic.bot.db.DBClient
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.bson.Document
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) = runBlocking {
    val mainJob = launch(CommonPool) {
        while (true) {
            val msg = TelegramClient.getUpdates()
            msg.forEach { m ->
                if (m is Map<*, *>) {
                    launch(CommonPool) {
                        saveCommand(m as Map<String, Any>)
                    }

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

    mainJob.join()
}

fun saveCommand(cmd: Map<String, Any>) {
    val docMsg = Document(cmd as MutableMap<String, Any>?)
    val messages = DBClient.getDatabase().getCollection("messages")
    messages.insertOne(docMsg)
}