package com.moses.linkedlineconnect.ui.theme.EscortDashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.moses.linkedlineconnect.data.AuthViewModel
import com.moses.linkedlineconnect.navigation.ROUTE_CHATPAGE
import com.moses.linkedlineconnect.navigation.ROUTE_LOGINParent

// Dark color palette
private val DarkNavy = Color(0xFF181C2A)
private val CardDark = Color(0xFF232946)
private val CardAccent = Color(0xFF393E5B)
private val ButtonBlue = Color(0xFF3A86FF)
private val ButtonGreen = Color(0xFF43AA8B)
private val ButtonOrange = Color(0xFFFF8800)
private val ButtonRed = Color(0xFFD7263D)
private val TextWhite = Color(0xFFF5F5F5)
private val TextGray = Color(0xFFB0B3C6)

data class AssignedRoute(
    val routeName: String,
    val schoolName: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EscortDashboardScreen(
    navController: NavController,
    authviewModel : AuthViewModel,
    onSendNotification: (String) -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    var assignedRoutes by remember { mutableStateOf<List<AssignedRoute>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }

    // Fetch assigned routes from Firebase (uncomment and implement as needed)
    // LaunchedEffect(Unit) {
    //     db.collection("assignedRoutes")
    //         .get()
    //         .addOnSuccessListener { result ->
    //             assignedRoutes = result.map { document ->
    //                 AssignedRoute(
    //                     routeName = document.getString("routeName") ?: "",
    //                     schoolName = document.getString("schoolName") ?: ""
    //                 )
    //             }
    //             loading = false
    //         }
    //         .addOnFailureListener {
    //             loading = false
    //         }
    // }

    Scaffold(
        containerColor = DarkNavy,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(DarkNavy, CardAccent)
                        )
                    )
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = "Escort Dashboard",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Loading Indicator
                if (loading) {
                    CircularProgressIndicator(color = ButtonBlue)
                } else {
                    // Assigned Routes List
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(assignedRoutes) { route ->
                            RouteCard(route = route)
                        }
                    }
                }

                // Chat Card
                ActionCard(
                    title = "Open Chat with Parents",
                    buttonText = "Open Chat",
                    buttonColor = ButtonBlue,
                    onClick = { navController.navigate(ROUTE_CHATPAGE) }
                )

                // Emergency Alert Card
                ActionCard(
                    title = "Send Emergency Alert",
                    buttonText = "Send Alert",
                    buttonColor = ButtonRed,
                    onClick = { onSendNotification("Emergency Alert") }
                )

                // Notify Arrived Card
                ActionCard(
                    title = "Notify Students Have Arrived",
                    buttonText = "Notify Arrived",
                    buttonColor = ButtonGreen,
                    onClick = { onSendNotification("Students Have Arrived") }
                )

                // Notify Boarded Card
                ActionCard(
                    title = "Notify Specific Students Have Boarded",
                    buttonText = "Notify Boarded",
                    buttonColor = ButtonOrange,
                    onClick = { onSendNotification("Students Have Boarded") }
                )

                // Logout Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(containerColor = CardAccent),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Logout",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = TextWhite
                        )
                        Button(
                            onClick = {
                                authviewModel.logout()
                                navController.navigate(ROUTE_LOGINParent) {
                                    popUpTo(0) { inclusive = true }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = ButtonRed)
                        ) {
                            Text("Logout", fontSize = 15.sp, color = Color.White)
                        }
                    }
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
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "Route: ${route.routeName}",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            Text(
                text = "School: ${route.schoolName}",
                fontSize = 15.sp,
                color = TextGray
            )
        }
    }
}

@Composable
fun ActionCard(
    title: String,
    buttonText: String,
    buttonColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = CardAccent),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = TextWhite
            )
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text(buttonText, fontSize = 15.sp, color = Color.White)
            }
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

