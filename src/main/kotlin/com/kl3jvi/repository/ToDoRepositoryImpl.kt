package com.kl3jvi.repository

import com.kl3jvi.models.ToDo


class ToDoRepositoryImpl : ToDoRepository {

    var list = mutableListOf(
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
}