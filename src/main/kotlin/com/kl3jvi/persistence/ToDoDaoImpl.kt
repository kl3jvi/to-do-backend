package com.kl3jvi.persistence

import com.kl3jvi.models.ToDo
import com.kl3jvi.models.ToDos
import com.kl3jvi.persistence.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ToDoDaoImpl : ToDoDao {

    /**
     * It takes a ResultRow and returns a ToDo
     *
     * @param row ResultRow - this is the row that is returned from the database.
     */
    private fun resultRowToToDo(row: ResultRow) = ToDo(
        id = row[ToDos.id],
        title = row[ToDos.title],
        done = row[ToDos.done],
    )

    /**
     * > We're using the `dbQuery` function to execute a query that selects all the rows from the `ToDos` table, and then
     * we're mapping each row to a `ToDo` object
     */
    override suspend fun allToDos(): List<ToDo> = dbQuery {
        ToDos.selectAll().map(::resultRowToToDo)
    }

    /**
     * > We're selecting the ToDo with the given id from the database, mapping the result to a ToDo object, and returning
     * the first result or null if there are no results
     *
     * @param id Int - The id of the ToDo we want to retrieve
     */
    override suspend fun toDo(id: Int): ToDo? = dbQuery {
        ToDos.select(ToDos.id eq id)
            .map(::resultRowToToDo)
            .singleOrNull()
    }

    /**
     * > We're inserting a new row into the `ToDos` table, and if the insert was successful, we're returning the new row as
     * a `ToDo` object
     *
     * @param title String, done: Boolean
     * @param done Boolean
     */
    override suspend fun addNewToDo(title: String, done: Boolean): ToDo? = dbQuery {
        val insertStatement = ToDos.insert {
            it[ToDos.title] = title
            it[ToDos.done] = done
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToToDo)
    }

    /**
     * "Update the row with the given id, setting the title and done columns to the given values, and return true if the
     * update was successful."
     *
     * @param id Int - The id of the ToDo item to edit
     * @param title The title of the todo
     * @param done Boolean
     */
    override suspend fun editToDo(id: Int, title: String, done: Boolean): Boolean = dbQuery {
        ToDos.update({ ToDos.id eq id }) {
            it[ToDos.title] = title
            it[ToDos.done] = done
        } > 0
    }

    /**
     * > Delete a ToDo from the database by its id
     *
     * @param id Int - The id of the todo to delete
     */
    override suspend fun deleteToDo(id: Int): Boolean = dbQuery {
        ToDos.deleteWhere { ToDos.id eq id } > 0
    }
}