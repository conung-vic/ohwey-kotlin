package com.conung.vic.bot.finance

import com.conung.vic.bot.db.DBClient
import com.mongodb.client.model.Filters.and
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Updates.set
import org.bson.Document
import org.bson.types.ObjectId
import org.slf4j.LoggerFactory

class Accounts {
    private val log = LoggerFactory.getLogger(Accounts::class.java)

    fun getBalance(chatId: Int, userId: Int): Int {
        log.debug("Requesting balance for userId = $userId and chatId = $chatId")
        val accounts = DBClient.getDatabase().getCollection("accounts")
        val doc = accounts.find(and(eq("user_id", userId), eq("chat_id", chatId))).first()
        val balance: Int = doc?.get("balance") as Int? ?: 0
        log.debug("Balance for userId = $userId and chatId = $chatId is $balance")
        return balance
    }

    fun changeBalance(chatId: Int, userId: Int, delta: Int) {
        log.debug("Changing balance for userId = $userId and chatId = $chatId")
        val accounts = DBClient.getDatabase().getCollection("accounts")
        val doc = accounts.find(and(eq("user_id", userId), eq("chat_id", chatId))).first()
        if (doc == null) {
            val newDoc = Document("user_id", userId).append("chat_id", chatId).append("balance", delta)
            accounts.insertOne(newDoc)
            log.debug("Balance for userId = $userId and chatId = $chatId is $delta")
        } else {
            val id: ObjectId = doc["_id"] as ObjectId? ?: ObjectId()
            val balance: Int = (doc["balance"] as Int? ?: 0) + delta
            accounts.updateOne(eq("_id", id), set("balance", balance))
            log.debug("Balance for userId = $userId and chatId = $chatId is $balance")
        }
    }
}