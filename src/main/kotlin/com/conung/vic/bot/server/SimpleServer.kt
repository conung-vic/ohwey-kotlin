package com.conung.vic.bot.server

import com.conung.vic.bot.Config
import com.conung.vic.bot.server.handlers.BotHandler
import com.conung.vic.bot.server.handlers.TestHandler
import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

fun main(args: Array<String>) {
    val securedPort: String = Config.getProperty(Config.KS_SEC_PORT)
    val port = Integer.valueOf(securedPort)

    val address = InetSocketAddress(port)
    val httpServer = HttpServer.create(address, 0)

    httpServer.createContext("/test", TestHandler())
    httpServer.createContext("/bot", BotHandler())
    httpServer.executor = null // creates a default executor
    httpServer.start()
}