package com.conung.vic.bot.server.handlers

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler

class TestHandler: HttpHandler {
    override fun handle(t: HttpExchange) {
        val response = "This is the response"
        t.responseHeaders.add("Access-Control-Allow-Origin", "*")
        t.sendResponseHeaders(200, response.length.toLong())
        val os = t.responseBody
        os.write(response.toByteArray())
        os.close()
    }
}