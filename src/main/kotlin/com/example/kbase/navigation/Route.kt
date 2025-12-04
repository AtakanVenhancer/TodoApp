package com.example.kbase.navigation

sealed interface Route {
    data object TodoList : Route
    data class Detail(val todoId: String) : Route
    data object Settings : Route
    data object AkbankScreen : Route
}

fun Route.label(): String = when (this) {
    Route.TodoList -> "TodoList"
    is Route.Detail -> "Detail:${todoId.take(4)}"
    Route.Settings -> "Settings"
    Route.AkbankScreen -> "AkbankScreen"
}
