package com.example.kbase.ui.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.kbase.navigation.Route

@Composable
fun AkbankScreen(onNavigate: (Route) -> Unit) {
    val akbankRed = Color(0xFFD32F2F)
    val view = LocalView.current

    SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = Color.White.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
    }

    Scaffold(
        topBar = { AkbankTopAppBar(akbankRed) },
        bottomBar = { AkbankBottomBar(akbankRed) }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .background(Color.White)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(48.dp)) // Increased top margin

                ProfileIconWithBadge(akbankRed)

                Spacer(modifier = Modifier.height(24.dp)) // Increased gap
                Text("İyi günler", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp)) // Increased gap
                Text("Atakan", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.height(24.dp)) // Increased gap
                Text("Yoksa Atakan değil misin?", fontSize = 14.sp, color = akbankRed, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(24.dp)) // Increased gap
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp), // Increased padding to narrow the button
                    colors = ButtonDefaults.buttonColors(containerColor = akbankRed),
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Text("Giriş", modifier = Modifier.padding(vertical = 8.dp))
                }
                Spacer(modifier = Modifier.height(40.dp)) // Increased gap
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = akbankRed,
                    modifier = Modifier
                        .size(40.dp)
                        .border(1.dp, Color.LightGray, CircleShape)
                        .padding(8.dp)
                )
            }
            Text(
                text = "V 4.60.0",
                color = Color.LightGray,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun ProfileIconWithBadge(badgeColor: Color) {
    Box {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFF0F0F0)),
            contentAlignment = Alignment.Center
        ) {
            Text("AA", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = badgeColor) // Changed to AA
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(24.dp)
                .clip(CircleShape)
                .background(badgeColor)
                .border(2.dp, Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = Icons.Default.Person, contentDescription = "", tint = Color.White, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun AkbankTopAppBar(selectedColor: Color) {
    TabRow(
        selectedTabIndex = 0,
        containerColor = Color.White,
        contentColor = selectedColor,
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[0])
                    .height(3.dp)
                    .background(selectedColor)
            )
        }
    ) {
        Tab(selected = true, onClick = { }, text = { Text("Bireysel", fontWeight = FontWeight.Bold) })
        Tab(selected = false, onClick = { }, text = { Text("Kurumsal") })
    }
}

@Composable
private fun AkbankBottomBar(backgroundColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Top
        ) {
            BottomBarItem(icon = Icons.Default.Speed, text = "FAST\nişlemleri")
            BottomBarItem(icon = Icons.Default.QrCodeScanner, text = "QR\nişlemleri")
            BottomBarItem(icon = Icons.Default.TrendingUp, text = "Fiyat ve\nOranlar")
            BottomBarItem(icon = Icons.Default.AccountBalance, text = "En yakın\nAkbank")
        }
        Text(
            text = "1 USD = 42.8082 TL",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
private fun BottomBarItem(icon: ImageVector, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = text, tint = Color(0xFFD32F2F), modifier = Modifier.size(28.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, color = Color.White, fontSize = 12.sp, textAlign = TextAlign.Center)
    }
}
