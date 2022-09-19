package com.kl3jvi.persistence

import com.kl3jvi.models.ToDos
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction



object DatabaseFactory {
    fun init() {
        val driverClassName = "com.mysql.cj.jdbc.Driver"
        val jdbcURL = "jdbc:mysql://remotemysql.com:3306/GQ7HytpfZt"
        val database = Database.connect(jdbcURL, driverClassName, user = "GQ7HytpfZt", password = "BEAy6gasWp")

        transaction(database) {
            SchemaUtils.create(ToDos)
        }
    }

    /* A suspending function that takes a suspending function as a parameter and returns a suspending function. */
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}