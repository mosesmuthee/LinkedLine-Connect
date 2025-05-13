package com.moses.linkedlineconnect.ui.theme.EscortDashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.moses.linkedlineconnect.navigation.ROUTE_CHATPAGE

data class AssignedRoute(
    val routeName: String,
    val schoolName: String
)

@Composable
fun EscortDashboardScreen(
    navController: NavController,
       onSendNotification: (String) -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    var assignedRoutes by remember { mutableStateOf<List<AssignedRoute>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }

    // Fetch assigned routes from Firebase
    LaunchedEffect(Unit) {
        db.collection("assignedRoutes")
            .get()
            .addOnSuccessListener { result ->
                assignedRoutes = result.map { document ->
                    AssignedRoute(
                        routeName = document.getString("routeName") ?: "",
                        schoolName = document.getString("schoolName") ?: ""
                    )
                }
                loading = false
            }
            .addOnFailureListener {
                loading = false
            }
    }

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
                    text = "Escort Dashboard",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6200EE),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Loading Indicator
                if (loading) {
                    CircularProgressIndicator(color = Color(0xFF6200EE))
                } else {
                    // Assigned Routes List
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(assignedRoutes) { route ->
                            RouteCard(route = route)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Chat Button
                Button(
                    onClick = { navController.navigate(ROUTE_CHATPAGE) }, // Replace "chat_screen" with the actual route name for ChatPageScreen
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                ) {
                    Text("Open Chat with Parents", fontSize = 16.sp, color = Color.White)
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Notify Buttons
                Button(
                    onClick = { onSendNotification("Emergency Alert") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))
                ) {
                    Text("Send Emergency Alert", fontSize = 16.sp, color = Color.White)
                }

                Button(
                    onClick = { onSendNotification("Students Have Arrived") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("Notify Students Have Arrived", fontSize = 16.sp, color = Color.White)
                }

                Button(
                    onClick = { onSendNotification("Specific Students Have Boarded") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                ) {
                    Text("Notify Specific Students Have Boarded", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    )
}

@Composable
fun RouteCard(route: AssignedRoute) {
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
            Text(
                text = "Route: ${route.routeName}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "School: ${route.schoolName}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

//@Preview
//@Composable
//fun EscortDashboardScreenPreview() {
//    val sampleRoutes = listOf(
//        AssignedRoute(routeName = "Route 1", schoolName = "ABC High School"),
//        AssignedRoute(routeName = "Route 2", schoolName = "XYZ Academy")
//    )
//    EscortDashboardScreen(
//        onNavigateToChat = {},
//        onSendNotification = {}
//    )
//}

