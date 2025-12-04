package com.example.kbase.ui.components

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kbase.R
import com.example.kbase.navigation.Route
import com.example.kbase.util.SupportedLocale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(onLocaleChange: (SupportedLocale) -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_title)) },
        actions = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.language_title),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(end = 4.dp)
                )
                LocalePicker(onChange = onLocaleChange)
            }
        }
    )
}

@Composable
private fun LocalePicker(onChange: (SupportedLocale) -> Unit) {
    val currentLocale = AppCompatDelegate.getApplicationLocales().toLanguageTags()
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        SupportedLocale.values().forEach { item ->
            val isActive = currentLocale.contains(item.tag)
            Button(
                onClick = { onChange(item) },
                enabled = !isActive,
                modifier = Modifier.height(32.dp)
            ) {
                Text(item.label, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(currentRoute: Route, onNavigate: (Route) -> Unit) {
    val destinations = listOf(
        Route.TodoList to ("Todos" to Icons.Outlined.List),
        Route.Settings to ("Settings" to Icons.Outlined.Settings)
    )
    NavigationBar {
        destinations.forEach { (route, labelIcon) ->
            val (label, icon) = labelIcon
            val isSelected = route::class == currentRoute::class
            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(route) },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) }
            )
        }
    }
}
