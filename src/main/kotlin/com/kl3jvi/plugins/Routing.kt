package com.kl3jvi.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") { call.respondText("Hello World!") }

        get("/todos") { }

        get("/todos/{id}") { }

        post("/todos") { }

        post("/todos/{id}") { }

        put("/todos/{id}") { }

        delete("/todos/{id}") { }

    }
}
