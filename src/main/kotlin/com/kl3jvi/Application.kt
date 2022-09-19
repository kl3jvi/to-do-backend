package com.kl3jvi

import com.kl3jvi.persistence.DatabaseFactory
import com.kl3jvi.plugins.configureCallLogging
import com.kl3jvi.plugins.configureKoinDI
import com.kl3jvi.plugins.configureRouting
import com.kl3jvi.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        DatabaseFactory.init()
        configureRouting()
        configureCallLogging()
        configureSerialization()
        configureKoinDI()
    }.start(wait = true)
}
