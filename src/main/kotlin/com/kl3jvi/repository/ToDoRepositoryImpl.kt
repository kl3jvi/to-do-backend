package com.kl3jvi.repository

import com.kl3jvi.models.ToDo
import com.kl3jvi.models.ToDoDraft
import com.kl3jvi.persistence.ToDoDao
import org.koin.core.component.KoinComponent


class ToDoRepositoryImpl(
    private val dao: ToDoDao
) : ToDoRepository {

    /**
     * It returns a list of ToDos.
     *
     * @return Either<List<ToDo>>
     */
    override suspend fun getAllTodos(): Either<List<ToDo>> {
        return runCatching {
            val list = dao.allToDos()
            if (list.isEmpty()) Either.error("Nothing found!")
            else Either.success(list.toList())
        }.getOrDefault(Either.error("Something went wrong"))
    }

    /**
     * > We're trying to get the first item in the list that matches the id, and if we can't, we return an error
     *
     * @param id Int - The id of the ToDo item to be retrieved
     * @return Either<ToDo?>
     */
    override suspend fun getToDo(id: Int): Either<ToDo?> {
        return runCatching {
            val todo = dao.toDo(id)
            Either.success(todo)
        }.getOrDefault(Either.error("Something went wrong"))
    }

    /**
     * It removes an item from the list by id.
     *
     * @param id The id of the item to be removed
     * @return Either<Boolean>
     */
    override suspend fun removeById(id: Int): Either<Boolean> {
        return runCatching {
            val transaction = dao.deleteToDo(id)
            Either.success(transaction)
        }.getOrDefault(Either.error("Something went wrong and couldn't add to db"))
    }

    /**
     * > We're trying to add a new ToDo to the list, and if it succeeds, we return the new ToDo, otherwise we return an
     * error
     *
     * @param draft ToDoDraft - This is the parameter that will be passed to the function.
     * @return Either<ToDo>
     */
    override suspend fun addTodo(draft: ToDoDraft): Either<ToDo?> {
        return runCatching {
            val transaction = dao.addNewToDo(draft.title, draft.done)
            Either.success(transaction)
        }.getOrDefault(Either.error("Something went wrong"))
    }

    /**
     * > If the todo exists, update it with the new values, otherwise return false
     *
     * @param id The id of the todo to update
     * @param draft ToDoDraft - This is the object that contains the new values for the todo.
     * @return Either<Boolean>
     */
    override suspend fun updateTodo(id: Int, draft: ToDoDraft): Either<Boolean> {
        return runCatching {
            val todo = dao.editToDo(id, draft.title, draft.done)
            Either.success(todo)
        }.getOrDefault(Either.error("Something went wrong"))
    }
}