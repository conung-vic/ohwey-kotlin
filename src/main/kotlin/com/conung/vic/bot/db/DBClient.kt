package com.conung.vic.bot.db

import com.conung.vic.bot.Config
import com.conung.vic.bot.Config.DB_CONNECTION_STRING
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoDatabase
import org.slf4j.LoggerFactory

object DBClient {
    private val log = LoggerFactory.getLogger(DBClient::class.java)

    private val database: MongoDatabase
    private val dbName: String
    private val mongoClient: MongoClient

    init {
        log.debug("DB connection initialization")
        val connectionString = Config[DB_CONNECTION_STRING] as String
        log.debug("Connection string = $connectionString")
        val uri = MongoClientURI(connectionString)
        dbName = uri.database
        mongoClient = MongoClient(uri)
        database = mongoClient.getDatabase(dbName)
        log.debug("DB connection initialized")
    }

    /*fun getMongoClient(): MongoClient {
        return this.mongoClient
    }*/

    fun getDatabase(): MongoDatabase = this.database

    /*fun shutdown() {
        mongoClient.close()
    }*/
}