package com.conung.vic.bot.server.handlers

import com.conung.vic.bot.ActionExecutor
import com.conung.vic.bot.actions.BalanceAction
import com.conung.vic.bot.client.beans.Update
import com.conung.vic.bot.db.DBClient
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import org.bson.Document
import org.slf4j.LoggerFactory

class BotHandler: HttpHandler {
    private val log = LoggerFactory.getLogger(BalanceAction::class.java)

    override fun handle(t: HttpExchange) {
        log.info("Got request")
        val size = t.requestBody.available()
        val buff = ByteArray(size)
        t.requestBody.read(buff)
        val body = String(buff)
        log.info("request: {}", body)

        saveCommand(body)

        val mapper = ObjectMapper().registerModule(KotlinModule())
        val update = mapper.readValue<Update>(body)
        ActionExecutor.parseMessage(update)

        val response = ""
        t.responseHeaders.add("Access-Control-Allow-Origin", "*")
        t.sendResponseHeaders(200, response.length.toLong())
        val os = t.responseBody
        os.write(response.toByteArray())
        os.close()
    }

    private fun saveCommand(cmd: String) {
        val docMsg = Document.parse(cmd)
        val messages = DBClient.getDatabase().getCollection("messages")
        messages.insertOne(docMsg)
    }
}