package com.example.ui.screens.classes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CardMembership
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
fun ManagePlansScreen() {
    val plans = listOf(
        PlanData("Beginner Hacker", "Ethical Hacker", "$10/mo", "Active"),
        PlanData("Pro Developer", "Developer", "$25/mo", "Active"),
        PlanData("Student Pro", "Student", "1000 Coins", "Inactive")
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add plan */ },
                containerColor = PurpleAccent,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Plan")
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
                items(plans.size) { index ->
                    PlanItem(plans[index])
                }
            }
        }
    }
}

data class PlanData(val name: String, val className: String, val price: String, val status: String)

@Composable
fun PlanItem(plan: PlanData) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(48.dp).clip(RoundedCornerShape(8.dp)).background(GoldAccent.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.CardMembership, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(24.dp))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(plan.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                    Text("Class: ${plan.className}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                IconButton(onClick = { /* Menu */ }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More Options", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(plan.price, style = MaterialTheme.typography.titleMedium, color = PurpleAccent, fontWeight = FontWeight.Bold)
                Badge(
                    containerColor = if (plan.status == "Active") com.example.ui.theme.GoldAccent else MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = if (plan.status == "Active") Color.Black else MaterialTheme.colorScheme.onSurfaceVariant
                ) {
                    Text(plan.status, modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp))
                }
            }
        }
    }
}
