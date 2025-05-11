package com.moses.linkedlineconnect.ui.theme.AdminScreens.AdminAssignEscortsPage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Route(
    val routeName: String,
    val assignedEscort: String? = null
)

@Composable
fun AdminAssignEscortsPageScreen(
    routes: List<Route>,
    escorts: List<String>,
    onConfirmAssignment: (Route, String) -> Unit
) {
    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = "Assign Escorts to Routes",
                    fontSize = 24.sp,
                    color = Color(0xFF6200EE),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Routes List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(routes) { route ->
                        RouteCard(
                            route = route,
                            escorts = escorts,
                            onConfirmAssignment = onConfirmAssignment
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun RouteCard(
    route: Route,
    escorts: List<String>,
    onConfirmAssignment: (Route, String) -> Unit
) {
    var selectedEscort by remember { mutableStateOf(route.assignedEscort ?: "") }
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Route Name
            Text(
                text = "Route: ${route.routeName}",
                fontSize = 16.sp,
                color = Color.Black
            )

            // Dropdown: Assign Escort
            Box {
                TextField(
                    value = selectedEscort,
                    onValueChange = {},
                    label = { Text("Assign Escort") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true },
                    readOnly = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedIndicatorColor = Color(0xFF6200EE),
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color(0xFF6200EE)
                    )
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    escorts.forEach { escort ->
                        DropdownMenuItem(
                            text = { Text(escort) },
                            onClick = {
                                selectedEscort = escort
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Confirm Assignment Button
            Button(
                onClick = { onConfirmAssignment(route, selectedEscort) },
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("Confirm Assignment", fontSize = 14.sp, color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun AdminAssignEscortsPageScreenPreview() {
    val sampleRoutes = listOf(
        Route(routeName = "Route 1"),
        Route(routeName = "Route 2"),
        Route(routeName = "Route 3")
    )
    val sampleEscorts = listOf("Escort A", "Escort B", "Escort C")

    AdminAssignEscortsPageScreen(
        routes = sampleRoutes,
        escorts = sampleEscorts,
        onConfirmAssignment = { route, escort ->
            // Handle assignment logic here
        }
    )
}

