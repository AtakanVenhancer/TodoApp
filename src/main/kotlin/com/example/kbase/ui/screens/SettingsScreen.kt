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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kbase.R
import com.example.kbase.data.Action
import com.example.kbase.data.AppState
import com.example.kbase.navigation.Route
import com.example.kbase.ui.components.AppTopBar
import com.example.kbase.ui.components.BottomNavigationBar
import com.example.kbase.util.SupportedLocale

@Composable
fun SettingsScreen(
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
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(stringResource(id = R.string.settings_title), style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { dispatch(Action.Reset) }, modifier = Modifier.fillMaxWidth()) {
                Text("Reset demo state")
            }
        }
    }
}
