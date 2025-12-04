package com.example.kbase.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kbase.R
import com.example.kbase.data.Action
import com.example.kbase.data.AppState
import com.example.kbase.navigation.Route
import com.example.kbase.ui.components.AppTopBar
import com.example.kbase.ui.components.BottomNavigationBar
import com.example.kbase.ui.components.HistoryList
import com.example.kbase.ui.components.TodoRow
import com.example.kbase.util.SupportedLocale

@Composable
fun TodoListScreen(
    state: AppState,
    dispatch: (Action) -> Unit,
    onLocaleChange: (SupportedLocale) -> Unit
) {
    Scaffold(
        topBar = { AppTopBar(onLocaleChange = onLocaleChange) },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = state.currentRoute,
                onNavigate = { dispatch(Action.Navigate(it)) }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.todo_title),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = stringResource(id = R.string.todo_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                OutlinedTextField(
                    value = state.newTodoTitle,
                    onValueChange = { dispatch(Action.UpdateNewTodoTitle(it)) },
                    label = { Text(stringResource(id = R.string.title_placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                OutlinedTextField(
                    value = state.newTodoNotes,
                    onValueChange = { dispatch(Action.UpdateNewTodoNotes(it)) },
                    label = { Text(stringResource(id = R.string.note_placeholder)) },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    maxLines = 4
                )
                Button(
                    onClick = { dispatch(Action.AddTodo) },
                    enabled = state.newTodoTitle.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(id = R.string.add_button))
                }

                Button(
                    onClick = { dispatch(Action.Navigate(Route.AkbankScreen)) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
                ) {
                    Text("Go to Akbank Screen")
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (state.todos.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.empty_todos),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                } else {
                    state.todos.forEach { todo ->
                        TodoRow(
                            todo = todo,
                            onToggle = { dispatch(Action.ToggleTodo(todo.id)) },
                            onDelete = { dispatch(Action.DeleteTodo(todo.id)) },
                            onOpenDetail = { dispatch(Action.OpenDetail(todo.id)) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.visit_history),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
                HistoryList(state.navigationHistory)
            }
        }
    }
}
