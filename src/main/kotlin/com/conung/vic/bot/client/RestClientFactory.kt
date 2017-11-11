package com.conung.vic.bot.client

import org.slf4j.LoggerFactory
import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder

object RestClientFactory {
    private val log = LoggerFactory.getLogger(RestClientFactory::class.java)
    var client: Client

    init {
        log.debug("Creating HTTP Client")
        client = ClientBuilder.newClient()
        log.debug("HTTP client successfully created")
    }


}