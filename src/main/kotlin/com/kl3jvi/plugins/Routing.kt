package com.kl3jvi.plugins

import com.kl3jvi.models.ToDoDraft
import com.kl3jvi.repository.Either
import com.kl3jvi.repository.ToDoRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val repository: ToDoRepositoryImpl by inject()
    routing {
        get("/") { call.respondText("Hello World!") }

        get("/todos") {
            when (val result = repository.getAllTodos()) {
                is Either.Error -> call.respond(result.message)
                is Either.Success -> call.respond(result.data)
            }
        }

        get("/todos/{id}") {
            val id =
                call.parameters["id"] ?: return@get call.respondText("Missing Id", status = HttpStatusCode.BadRequest)

            when (val result = repository.getToDo(id = id.toInt())) {
                is Either.Error -> call.respond(result.message)

                is Either.Success -> {
                    if (result.data != null) {
                        call.respond(result.data)
                    } else call.respondText("No todo found with id $id", status = HttpStatusCode.NotFound)
                }
            }
        }

        post("/todos") {
            val draftToDo = call.receive<ToDoDraft>()
            when (val result = repository.addTodo(draftToDo)) {
                is Either.Error -> call.respond(result.message)
                is Either.Success -> call.respond(result.data)
            }
            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }

        post("/todos/{id}") {

        }

        put("/todos/{id}") { }

        delete("/todos/{id}") {
//            val id
        }

    }
}
