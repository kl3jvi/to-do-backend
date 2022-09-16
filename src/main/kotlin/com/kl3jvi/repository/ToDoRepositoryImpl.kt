package com.kl3jvi.repository

import com.kl3jvi.models.ToDo
import com.kl3jvi.models.ToDoDraft


class ToDoRepositoryImpl : ToDoRepository {

    private var list = mutableListOf(
        ToDo(1, "Title", true),
        ToDo(2, "Naruto", false),
        ToDo(3, "Ardit", false),
        ToDo(4, "Klejvi", true),
        ToDo(5, "Kristi", false),
        ToDo(6, "Flak", true),
        ToDo(7, "Tarzani", false),
    )

    /**
     * It returns a list of ToDos.
     *
     * @return Either<List<ToDo>>
     */
    override fun getAllTodos(): Either<List<ToDo>> {
        return runCatching {
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
    override fun getToDo(id: Int): Either<ToDo?> {
        return runCatching {
            Either.success(list.firstOrNull { it.id == id })
        }.getOrDefault(Either.error("Something went wrong"))
    }

    /**
     * It removes an item from the list by id.
     *
     * @param id The id of the item to be removed
     * @return Either<Boolean>
     */
    override fun removeById(id: Int): Either<Boolean> {
        return runCatching {
            Either.success(list.removeIf { it.id == id })
        }.getOrDefault(Either.error("Something went wrong and couldn't add to db"))
    }

    /**
     * > We're trying to add a new ToDo to the list, and if it succeeds, we return the new ToDo, otherwise we return an
     * error
     *
     * @param draft ToDoDraft - This is the parameter that will be passed to the function.
     * @return Either<ToDo>
     */
    override fun addTodo(draft: ToDoDraft): Either<ToDo> {
        return runCatching {
            val toDo = ToDo(
                id = list.size + 1, title = draft.title, done = draft.done
            )
            list.add(toDo)
            Either.success(toDo)
        }.getOrDefault(Either.error("Something went wrong"))
    }

    /**
     * > If the todo exists, update it with the new values, otherwise return false
     *
     * @param id The id of the todo to update
     * @param draft ToDoDraft - This is the object that contains the new values for the todo.
     * @return Either<Boolean>
     */
    override fun updateTodo(id: Int, draft: ToDoDraft): Either<Boolean> {
        return runCatching {
            val todo = list.firstOrNull { it.id == id }

            val result = if (todo != null) {
                todo.title = draft.title
                todo.done = draft.done
                true
            } else false

            Either.success(result)
        }.getOrDefault(Either.error("Something went wrong"))
    }
}