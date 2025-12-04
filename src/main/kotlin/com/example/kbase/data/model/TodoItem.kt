package com.example.kbase.data.model

import java.util.UUID

data class TodoItem(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val notes: String,
    val isDone: Boolean = false
)
