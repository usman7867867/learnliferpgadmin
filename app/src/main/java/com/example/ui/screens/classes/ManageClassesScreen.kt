package com.example.ui.screens.classes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.PurpleAccent
import com.example.ui.theme.GoldAccent

@Composable
fun ManageClassesScreen() {
    val classes = listOf(
        ClassData("Student", "Basic student resources", "Active"),
        ClassData("Developer", "Coding and software engineering", "Active"),
        ClassData("Entrepreneur", "Business and startup", "Active"),
        ClassData("Ethical Hacker", "Cyber security fundamentals", "Inactive")
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add class */ },
                containerColor = PurpleAccent,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Class")
            }
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp, top = 16.dp)
            ) {
                items(classes.size) { index ->
                    ClassItem(classes[index])
                }
            }
        }
    }
}

data class ClassData(val name: String, val description: String, val status: String)

@Composable
fun ClassItem(classData: ClassData) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(56.dp).clip(RoundedCornerShape(12.dp)).background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Class, contentDescription = null, tint = PurpleAccent, modifier = Modifier.size(28.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(classData.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                Text(classData.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Badge(
                containerColor = if (classData.status == "Active") GoldAccent else MaterialTheme.colorScheme.surfaceVariant,
                contentColor = if (classData.status == "Active") Color.Black else MaterialTheme.colorScheme.onSurfaceVariant
            ) {
                Text(classData.status, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
            }
            IconButton(onClick = { /* Menu */ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More Options", tint = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}
