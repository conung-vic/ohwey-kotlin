package com.conung.vic.bot.client

import com.conung.vic.bot.Config
import com.conung.vic.bot.Config.API
import com.conung.vic.bot.Config.BOT_TOKEN
import org.slf4j.LoggerFactory
import java.util.*
import javax.ws.rs.client.Entity
import javax.ws.rs.core.GenericType
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.UriBuilder

object TelegramClient {
    private val log = LoggerFactory.getLogger(TelegramClient::class.java)
    private val client = RestClientFactory.client
    private val baseApi = (Config[API] as String) + Config[BOT_TOKEN]
    private var offset = 0

    fun getUpdates():List<*> {
        val url =  baseApi + "/getUpdates?offset=$offset"
        log.debug("API request: address = $url")
        val uri = UriBuilder.fromUri(url).build(null).normalize()
        val resource = client.target(uri)

        val result = resource.request(MediaType.APPLICATION_JSON).get()
        val list = LinkedList<Any>()

        if (result.hasEntity()) {
            val mapGeneric = GenericType.forInstance(HashMap<String, Any>())
            val res = result.readEntity(mapGeneric)
            if (res is HashMap<*, *>) {
                val updates = res["result"]
                if (res["ok"] as Boolean && updates is List<*>) {
                    updates.forEach { update ->
                        if (update is Map<*, *>) {
                            offset = update["update_id"] as Int + 1
                            val msg = update["message"]
                            if (msg != null) {
                                list.add(msg)
                            }
                        }
                    }
                }
            }
        }
        return list
    }

    fun sendMessage(msg: Map<*, *>) {
        val url = baseApi + "/sendMessage"
        log.debug("API request: address = $url")
        val uri = UriBuilder.fromUri(url).build(null).normalize()
        val resource = client.target(uri)

        resource.request(MediaType.APPLICATION_JSON).post(Entity.json(msg))
        log.debug("Message sent")
    }
}