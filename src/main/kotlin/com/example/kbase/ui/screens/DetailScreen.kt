package com.example.kbase.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kbase.R
import com.example.kbase.data.AppState
import com.example.kbase.util.SupportedLocale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    state: AppState,
    todoId: String,
    onBack: () -> Unit,
    onLocaleChange: (SupportedLocale) -> Unit // Keep for consistency, though not used in TopAppBar
) {
    val todo = state.todos.find { it.id == todoId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.detail_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_to_list)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(stringResource(id = R.string.detail_title), style = MaterialTheme.typography.headlineMedium)
            if (todo == null) {
                Text(stringResource(id = R.string.detail_fallback))
            } else {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(todo.title, style = MaterialTheme.typography.titleLarge)
                        Text(
                            todo.notes.ifBlank { "-" },
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            if (todo.isDone) "✅ Completed" else "🕘 Pending",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
