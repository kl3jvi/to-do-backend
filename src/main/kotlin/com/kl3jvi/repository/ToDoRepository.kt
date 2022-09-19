package com.kl3jvi.repository

import com.kl3jvi.models.ToDo
import com.kl3jvi.models.ToDoDraft

interface ToDoRepository {
    suspend fun getAllTodos(): Either<List<ToDo>>
    suspend fun removeById(id: Int): Either<Boolean>
    suspend fun getToDo(id: Int): Either<ToDo?>
    suspend fun addTodo(draft: ToDoDraft): Either<ToDo?>
    suspend fun updateTodo(id: Int, draft: ToDoDraft): Either<Boolean>
}