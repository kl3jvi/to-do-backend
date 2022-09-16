package com.kl3jvi.repository

import com.kl3jvi.models.ToDo

interface ToDoRepository {
    fun getAllTodos(): Either<List<ToDo>>
    fun removeById(id: Int): Either<Boolean>
    fun getToDo(id: Int): Either<ToDo?>
}