package com.conung.vic.bot.server.handlers

import com.conung.vic.bot.actions.BalanceAction
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import org.slf4j.LoggerFactory

class BotHandler: HttpHandler {
    private val log = LoggerFactory.getLogger(BalanceAction::class.java)

    override fun handle(t: HttpExchange) {
        log.info("Got request")
        val size = t.requestBody.available()
        val buff = ByteArray(size)
        t.requestBody.read(buff)
        val body : String = String(buff);
        log.info("request: {}", body)

        val response = ""
        t.responseHeaders.add("Access-Control-Allow-Origin", "*")
        t.sendResponseHeaders(200, response.length.toLong())
        val os = t.responseBody
        os.write(response.toByteArray())
        os.close()
    }
}