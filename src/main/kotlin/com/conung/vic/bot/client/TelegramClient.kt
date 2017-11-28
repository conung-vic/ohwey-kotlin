package com.conung.vic.bot.client

import com.conung.vic.bot.Config
import com.conung.vic.bot.Config.API
import com.conung.vic.bot.Config.BOT_TOKEN
import com.conung.vic.bot.client.beans.TelegramResponce
import com.conung.vic.bot.client.beans.Update
import org.slf4j.LoggerFactory
import java.util.*
import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.UriBuilder

object TelegramClient {
    private val log = LoggerFactory.getLogger(TelegramClient::class.java)
    private val client = RestClientFactory.client
    private val baseApi = (Config[API] as String) + Config[BOT_TOKEN]
    private var offset = 0

    fun getUpdates():List<Update> {
        val url =  baseApi + "/getUpdates?offset=$offset"
        log.debug("API request: address = $url")
        val uri = UriBuilder.fromUri(url).build(null).normalize()
        val resource = client.target(uri)

        val result = resource.request(MediaType.APPLICATION_JSON).get(TelegramResponce::class.java)
        return if (result.ok) {
            result.result.forEach {
                update -> offset = update.updateId+1
            }
            result.result
        } else {
            LinkedList()
        }
    }

    fun sendMessage(msg: Map<*, *>) {
        val url = baseApi + "/sendMessage"
        send(msg, url)
    }

    fun sendDocument(msg: Map<*, *>) {
        val url = baseApi + "/sendDocument"
        send(msg, url)
    }

    private fun send(msg: Map<*, *>, url: String) {
        log.debug("API request: address = $url")
        val uri = UriBuilder.fromUri(url).build(null).normalize()
        val resource = client.target(uri)

        resource.request(MediaType.APPLICATION_JSON).post(Entity.json(msg))
        log.debug("Message sent")
    }
}