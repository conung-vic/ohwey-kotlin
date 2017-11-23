package com.conung.vic.bot

import com.conung.vic.bot.client.TelegramClient
import com.conung.vic.bot.client.beans.Update
import com.conung.vic.bot.db.DBClient
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.bson.Document
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) = runBlocking {
    val mainJob = launch(CommonPool) {
        while (true) {
            val msgList = TelegramClient.getUpdates()
            msgList.forEach { update ->
                launch(CommonPool) {
                    saveCommand(update)
                }
                ActionExecutor.parseMessage(update)
            }
            TimeUnit.SECONDS.sleep(2)
        }
    }

    mainJob.join()
}

fun saveCommand(cmd: Update) {
    val om = ObjectMapper()
    val string = om.writeValueAsString(cmd)
    val docMsg = Document.parse(string)
    val messages = DBClient.getDatabase().getCollection("messages")
    messages.insertOne(docMsg)
}