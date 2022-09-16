package com.kl3jvi.models

import kotlinx.serialization.Serializable

@Serializable
data class ToDo(
    val id: Int,
    val title: String,
    val done: Boolean
)
