package com.kl3jvi.persistence

import com.kl3jvi.models.ToDo

interface ToDoDao {
    suspend fun allToDos(): List<ToDo>
    suspend fun toDo(id: Int): ToDo?
    suspend fun editToDo(id: Int, title: String, done: Boolean): Boolean
    suspend fun deleteToDo(id: Int): Boolean
    suspend fun addNewToDo(title: String, done: Boolean): ToDo?
}