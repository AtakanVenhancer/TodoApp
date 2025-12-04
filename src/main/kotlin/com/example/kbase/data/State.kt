package com.example.kbase.data

import com.example.kbase.data.model.TodoItem
import com.example.kbase.navigation.Route
import com.example.kbase.navigation.label
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class AppState(
    val currentRoute: Route = Route.TodoList,
    val todos: List<TodoItem> = sampleTodos(),
    val newTodoTitle: String = "",
    val newTodoNotes: String = "",
    val navigationHistory: List<String> = listOf("TodoList")
)

sealed interface Action {
    data class Navigate(val route: Route) : Action
    data class UpdateNewTodoTitle(val text: String) : Action
    data class UpdateNewTodoNotes(val text: String) : Action
    data class ToggleTodo(val id: String) : Action
    data class DeleteTodo(val id: String) : Action
    data class OpenDetail(val id: String) : Action
    data object AddTodo : Action
    data object Reset : Action
}

class StateStore {
    private val _state = MutableStateFlow(AppState())
    val state: StateFlow<AppState> = _state

    fun dispatch(action: Action) {
        _state.update { current ->
            when (action) {
                is Action.Navigate -> current.copy(
                    currentRoute = action.route,
                    navigationHistory = (current.navigationHistory + action.route.label()).takeLast(8)
                )

                is Action.UpdateNewTodoTitle -> current.copy(newTodoTitle = action.text)
                is Action.UpdateNewTodoNotes -> current.copy(newTodoNotes = action.text)

                Action.AddTodo -> {
                    if (current.newTodoTitle.isBlank()) current
                    else {
                        val newTodo = TodoItem(
                            title = current.newTodoTitle.trim(),
                            notes = current.newTodoNotes.trim()
                        )
                        current.copy(
                            todos = listOf(newTodo) + current.todos,
                            newTodoTitle = "",
                            newTodoNotes = "",
                            navigationHistory = (current.navigationHistory + "AddTodo").takeLast(8)
                        )
                    }
                }

                is Action.ToggleTodo -> current.copy(
                    todos = current.todos.map {
                        if (it.id == action.id) it.copy(isDone = !it.isDone) else it
                    }
                )

                is Action.DeleteTodo -> current.copy(
                    todos = current.todos.filterNot { it.id == action.id },
                    navigationHistory = (current.navigationHistory + "DeleteTodo").takeLast(8)
                )

                is Action.OpenDetail -> current.copy(
                    currentRoute = Route.Detail(action.id),
                    navigationHistory = (current.navigationHistory + "Detail:${action.id.take(4)}").takeLast(8)
                )

                Action.Reset -> AppState()
            }
        }
    }
}

fun sampleTodos(): List<TodoItem> = listOf(
    TodoItem(title = "Compose ile landing ekranını bitir", notes = "Listeler mobilde %100 genişlikte olsun."),
    TodoItem(title = "Firebase senkronizasyonunu bağla", notes = "Kotlin Multiplatform için shared module."),
    TodoItem(title = "Beta kullanıcılarına dağıt", notes = "Internal testing track")
)
