package com.example.ui.screens.support

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Support
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ui.theme.PurpleAccent
import com.example.ui.theme.GoldAccent

@Composable
fun ManageSupportScreen() {
    val tickets = listOf(
        TicketData("Cannot access Day 5 tasks", "User123", "Open"),
        TicketData("Issue with JazzCash payment", "StudentHacker", "In Progress"),
        TicketData("Discord role not assigned", "DevOpsGuy", "Closed")
    )

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp, top = 16.dp)
        ) {
            items(tickets.size) { index ->
                TicketItem(tickets[index])
            }
        }
    }
}

data class TicketData(val issue: String, val author: String, val status: String)

@Composable
fun TicketItem(ticket: TicketData) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        onClick = { /* Open chat */ }
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(40.dp).clip(CircleShape).background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Support, contentDescription = null, tint = PurpleAccent)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(ticket.issue, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text("From: ${ticket.author}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Spacer(modifier = Modifier.width(8.dp))
            
            val statusColor = when (ticket.status) {
                "Open" -> MaterialTheme.colorScheme.error
                "In Progress" -> GoldAccent
                else -> Color.Gray
            }
            
            Badge(
                containerColor = statusColor.copy(alpha = 0.2f),
                contentColor = statusColor
            ) {
                Text(ticket.status, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
            }
            
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
