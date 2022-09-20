package com.kl3jvi.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import org.slf4j.event.Level

fun Application.configureCallLogging() {
    install(CallLogging) {
        this.level = Level.DEBUG
    }
}