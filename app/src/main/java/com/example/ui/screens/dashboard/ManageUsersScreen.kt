package com.example.ui.screens.dashboard

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.example.ui.theme.PurpleAccent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageUsersScreen() {
    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        delay(2000) // Simulate network request
        isLoading = false
    }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search users...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PurpleAccent,
                unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (isLoading) {
                items(10) {
                    UserSkeletonItem()
                }
            } else {
                items(10) { index ->
                    UserItem(
                        name = "User ${index + 1}",
                        email = "user${index + 1}@gmail.com",
                        role = if (index % 3 == 0) "Premium" else "Free"
                    )
                }
            }
        }
    }
}

@Composable
fun UserItem(name: String, email: String, role: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(48.dp).clip(CircleShape).background(PurpleAccent),
                contentAlignment = Alignment.Center
            ) {
                Text(name.first().toString(), color = Color.White)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(name, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                Text(email, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Badge(
                containerColor = if (role == "Premium") com.example.ui.theme.GoldAccent else MaterialTheme.colorScheme.surfaceVariant,
                contentColor = if (role == "Premium") Color.Black else MaterialTheme.colorScheme.onSurfaceVariant
            ) {
                Text(role, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
            }
        }
    }
}

@Composable
fun UserSkeletonItem() {
    val transition = rememberInfiniteTransition()
    val alpha by transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(48.dp).clip(CircleShape).background(Color.Gray.copy(alpha = alpha))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Box(modifier = Modifier.height(20.dp).fillMaxWidth(0.6f).clip(RoundedCornerShape(4.dp)).background(Color.Gray.copy(alpha = alpha)))
                Spacer(modifier = Modifier.height(8.dp))
                Box(modifier = Modifier.height(14.dp).fillMaxWidth(0.4f).clip(RoundedCornerShape(4.dp)).background(Color.Gray.copy(alpha = alpha)))
            }
            Box(modifier = Modifier.height(24.dp).width(60.dp).clip(RoundedCornerShape(12.dp)).background(Color.Gray.copy(alpha = alpha)))
        }
    }
}
