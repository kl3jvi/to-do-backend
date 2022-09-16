package com.kl3jvi.repository

import com.kl3jvi.models.ToDo
import com.kl3jvi.models.ToDoDraft

interface ToDoRepository {
    fun getAllTodos(): Either<List<ToDo>>
    fun removeById(id: Int): Either<Boolean>
    fun getToDo(id: Int): Either<ToDo?>
    fun addTodo(draft: ToDoDraft): Either<ToDo>
    fun updateTodo(id: Int, draft: ToDoDraft): Either<Boolean>
}