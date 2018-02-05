package com.conung.vic.bot.server

import com.conung.vic.bot.Config
import com.conung.vic.bot.Config.KS_KEY_PWD
import com.conung.vic.bot.Config.KS_PATH
import com.conung.vic.bot.Config.KS_PWD
import com.conung.vic.bot.Config.KS_SEC_PORT
import com.conung.vic.bot.server.handlers.BotHandler
import com.conung.vic.bot.server.handlers.TestHandler
import com.sun.net.httpserver.HttpsConfigurator
import com.sun.net.httpserver.HttpsParameters
import com.sun.net.httpserver.HttpsServer
import com.sun.net.ssl.internal.ssl.Provider
import java.io.FileInputStream
import java.net.InetSocketAddress
import java.security.KeyStore
import java.security.Security
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory


fun main(args: Array<String>) {
    val ksPath: String = Config.getProperty(KS_PATH)
    val keystorePwd: CharArray = Config.getProperty(KS_PWD).toCharArray()
    val keyPwd: CharArray = Config.getProperty(KS_KEY_PWD).toCharArray()
    val securedPort: String = Config.getProperty(KS_SEC_PORT)
    val port = Integer.valueOf(securedPort)

    Security.addProvider(Provider())
    val keystore = KeyStore.getInstance("JKS")
    keystore.load(FileInputStream(ksPath), keystorePwd)

    val kmf = KeyManagerFactory.getInstance("SunX509")
    kmf.init(keystore, keyPwd)

    val tmf = TrustManagerFactory.getInstance("SunX509")
    tmf.init(keystore)

    val sslc = SSLContext.getInstance("SSLv3")
    sslc.init(kmf.keyManagers, tmf.trustManagers, null)

    val address = InetSocketAddress(port)
    val httpsServer = HttpsServer.create(address, 0)
    httpsServer.httpsConfigurator = object : HttpsConfigurator(sslc) {
        override fun configure(params: HttpsParameters) {
            try {
                // initialise the SSL context
                val c = SSLContext.getDefault()
                val engine = c.createSSLEngine()
                params.needClientAuth = false
                params.cipherSuites = engine.enabledCipherSuites
                params.protocols = engine.enabledProtocols

                // get the default parameters
                val defaultSSLParameters = c.defaultSSLParameters
                params.setSSLParameters(defaultSSLParameters)

            } catch (ex: Exception) {
                println("Failed to create HTTPS port")
            }

        }
    }
    httpsServer.createContext("/test", TestHandler())
    httpsServer.createContext("/bot", BotHandler())
    httpsServer.executor = null // creates a default executor
    httpsServer.start()
}