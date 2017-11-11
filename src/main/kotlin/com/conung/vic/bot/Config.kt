package com.conung.vic.bot

import com.conung.vic.bot.exceptions.InvalidConfigException
import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*

object Config: Properties() {
    private val log = LoggerFactory.getLogger(Config::class.java)
    private val FILE_LOCATION_ENV_VAR = "OHWEY_CONFIG_FILE"
    private val DB_CONNECTION_STRING = "bot-connection-string"
    private val DB_HOST = "bot-host-name"
    private val DB_NAME = "bot-db-name"
    private val DB_PORT = "bot-db-port"
    val BOT_TOKEN = "bot-token"
    val API = "bot-api"

    init {
        log.info("Start of configuration initialization")

        val inputStream: InputStream
        val file: File

        val fileLocation = System.getenv()[FILE_LOCATION_ENV_VAR]
        if (fileLocation != null) {
            file = File(fileLocation)
            if (file.exists() && file.isFile) {
                log.info("Using external configuratin file: $fileLocation")
                inputStream = FileInputStream(file)
            } else {
                val msg = "Env var $FILE_LOCATION_ENV_VAR points to wrong location: $fileLocation"
                log.error(msg)
                throw InvalidConfigException(msg)
            }
        } else {
            log.info("Env var $FILE_LOCATION_ENV_VAR is not set. Usong default config")
            inputStream = Thread.currentThread().contextClassLoader.getResourceAsStream("bot_config.ini")
        }

        try {
            this.load(inputStream)
            log.info("Configuration initialized")
        } catch (e: Exception) {
            log.warn("Failed to load configuration", e)
            throw InvalidConfigException("Failed to load configuration", e)
        } finally {
            inputStream?.close()
        }
        log.debug("Applied configuration is:")
        this.keys.forEach{ key -> log.debug("$key = ${this[key]}")}
    }
}