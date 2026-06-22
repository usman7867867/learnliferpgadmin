package com.example.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.ui.screens.classes.ManageClassesScreen
import com.example.ui.screens.classes.ManagePlansScreen
import com.example.ui.theme.PurpleAccent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardLayout(onLogout: () -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var currentRoute by remember { mutableStateOf("home") }

    val menuItems = listOf(
        "home" to Pair("Dashboard", Icons.Default.Dashboard),
        "users" to Pair("Users", Icons.Default.People),
        "classes" to Pair("Classes", Icons.Default.Class),
        "plans" to Pair("Plans", Icons.Default.CardMembership),
        "tasks" to Pair("Tasks", Icons.AutoMirrored.Filled.List),
        "subscriptions" to Pair("Subscriptions", Icons.Default.Payment),
        "referrals" to Pair("Referrals", Icons.Default.GroupAdd),
        "announcements" to Pair("Announcements", Icons.Default.Campaign),
        "support" to Pair("Support Tickets", Icons.Default.Support),
        "settings" to Pair("App Settings", Icons.Default.Settings),
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                Text(
                    "LEARN LIFE RPG",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = PurpleAccent,
                    fontWeight = FontWeight.Bold
                )
                HorizontalDivider()
                menuItems.forEach { (route, info) ->
                    NavigationDrawerItem(
                        icon = { Icon(info.second, contentDescription = null) },
                        label = { Text(info.first) },
                        selected = route == currentRoute,
                        onClick = {
                            currentRoute = route
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
                Spacer(Modifier.weight(1f))
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.ExitToApp, contentDescription = null, tint = MaterialTheme.colorScheme.error) },
                    label = { Text("Logout", color = MaterialTheme.colorScheme.error) },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        onLogout()
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                Spacer(Modifier.height(24.dp))
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(menuItems.find { it.first == currentRoute }?.second?.first ?: "Admin") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface,
                        navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                when (currentRoute) {
                    "home" -> DashboardHome(onNavigate = { route -> currentRoute = route })
                    "users" -> ManageUsersScreen()
                    "classes" -> ManageClassesScreen()
                    "plans" -> ManagePlansScreen()
                    "tasks" -> com.example.ui.screens.tasks.ManageTasksScreen()
                    "support" -> com.example.ui.screens.support.ManageSupportScreen()
                    "announcements" -> com.example.ui.screens.announcements.ManageAnnouncementsScreen()
                    else -> CenterPlaceholder(currentRoute.replaceFirstChar { it.uppercase() })
                }
            }
        }
    }
}

@Composable
fun CenterPlaceholder(title: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
        Text("Manage $title - Coming Soon", color = MaterialTheme.colorScheme.onBackground)
    }
}
