package com.kl3jvi.plugins


import com.kl3jvi.di.persistenceModule
import com.kl3jvi.di.repositoryModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoinDI() {
    install(Koin) {
        slf4jLogger()
        modules(
            repositoryModule, persistenceModule
        )
    }
}

