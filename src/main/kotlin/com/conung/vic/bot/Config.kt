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
    val DB_CONNECTION_STRING = "bot-connection-string"
    // val DB_HOST = "bot-host-name"
    // val DB_NAME = "bot-db-name"
    // val DB_PORT = "bot-db-port"
    val BOT_TOKEN = "bot-token"
    val API = "bot-api"

    val KS_PATH = "bot-keycert-path"
    val KS_PWD = "bot-keycert-store-pwd"
    val KS_KEY_PWD = "bot-keycert-key-pwd"
    val KS_SEC_PORT = "bot-port"

    init {
        log.info("Start of configuration initialization")

        val inputStream: InputStream
        val file: File

        val fileLocation = System.getenv()[FILE_LOCATION_ENV_VAR]
        if (fileLocation != null) {
            file = File(fileLocation)
            if (file.exists() && file.isFile) {
                log.info("Using external configuration file: $fileLocation")
                inputStream = FileInputStream(file)
            } else {
                val msg = "Env var $FILE_LOCATION_ENV_VAR points to wrong location: $fileLocation"
                log.error(msg)
                throw InvalidConfigException(msg)
            }
        } else {
            log.info("Env var $FILE_LOCATION_ENV_VAR is not set. Using default config")
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