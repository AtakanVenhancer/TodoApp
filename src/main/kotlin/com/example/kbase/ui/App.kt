package com.example.kbase.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.kbase.data.Action
import com.example.kbase.data.StateStore
import com.example.kbase.navigation.Route
import com.example.kbase.ui.screens.AkbankScreen
import com.example.kbase.ui.screens.DetailScreen
import com.example.kbase.ui.screens.SettingsScreen
import com.example.kbase.ui.screens.TodoListScreen
import com.example.kbase.util.SupportedLocale

@Composable
fun KotlinGuideApp(store: StateStore, onLocaleChange: (SupportedLocale) -> Unit) {
    val uiState by store.state.collectAsState()

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            when (val route = uiState.currentRoute) {
                Route.TodoList -> TodoListScreen(
                    state = uiState,
                    dispatch = store::dispatch,
                    onLocaleChange = onLocaleChange
                )

                is Route.Detail -> DetailScreen(
                    state = uiState,
                    todoId = route.todoId,
                    onBack = { store.dispatch(Action.Navigate(Route.TodoList)) },
                    onLocaleChange = onLocaleChange
                )

                Route.Settings -> SettingsScreen(
                    state = uiState,
                    dispatch = store::dispatch,
                    onLocaleChange = onLocaleChange
                )
                
                Route.AkbankScreen -> AkbankScreen(
                    onNavigate = { store.dispatch(Action.Navigate(it)) }
                )
            }
        }
    }
}
