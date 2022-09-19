package com.kl3jvi.plugins

import com.kl3jvi.models.ToDoDraft
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
        get("/") { call.respond(HttpStatusCode.NotFound, "Use other url routes") }

        get("/todos") {
            val result = repository.getAllTodos()

            result.onSuccess {
                call.respond(it)
            }.onFailure {
                call.respond(it)
            }
        }

        get("/todos/{id}") {
            val id =
                call.parameters["id"] ?: return@get call.respondText("Missing Id", status = HttpStatusCode.BadRequest)
            val result = repository.getToDo(id = id.toInt())

            result.onSuccess {
                if (it != null) {
                    call.respond(it)
                } else call.respondText("No todo found with id $id", status = HttpStatusCode.NotFound)
            }.onFailure {
                call.respond(it)
            }

        }

        post("/todos") {
            val draftToDo = call.receive<ToDoDraft>()
            val result = repository.addTodo(draftToDo)
            result.onSuccess {
                if (it != null)
                    call.respond(it)
                call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
            }.onFailure {
                call.respond(it)
                call.respondText("Customer Not stored correctly", status = HttpStatusCode.BadRequest)
            }
        }


        put("/todos/{id}") {
            val toDoDraft = call.receive<ToDoDraft>()
            val todoId = call.parameters["id"]

            if (todoId == null) {
                call.respond(HttpStatusCode.BadRequest, "Id has to be a number")
                return@put
            }

            val result = repository.updateTodo(todoId.toInt(), toDoDraft)
            result.onSuccess {
                call.respond(HttpStatusCode.OK, it)
            }.onFailure {
                call.respond(HttpStatusCode.NotFound, it)
            }

        }

        delete("/todos/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            val response = repository.removeById(id.toInt())

            response.onSuccess {
                call.respondText("Todo removed correctly", status = HttpStatusCode.Accepted)
            }.onFailure {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}





