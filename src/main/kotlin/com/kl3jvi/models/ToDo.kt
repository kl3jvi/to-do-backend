package com.kl3jvi.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class ToDo(
    val id: Int,
    var title: String,
    var done: Boolean
)

object ToDos : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 128)
    val done = bool("done")

    override val primaryKey = PrimaryKey(id)
}
