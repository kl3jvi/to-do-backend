package com.kl3jvi.models

import kotlinx.serialization.Serializable

@Serializable
data class ToDoDraft(
    val title: String,
    val done: Boolean
)