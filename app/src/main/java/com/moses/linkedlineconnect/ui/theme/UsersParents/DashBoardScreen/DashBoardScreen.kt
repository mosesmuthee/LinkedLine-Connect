package com.moses.linkedlineconnect.ui.theme.UsersParents.DashBoardScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.moses.linkedlineconnect.data.AuthViewModel
import com.moses.linkedlineconnect.navigation.ROUTE_BOOKINGPAGE
import com.moses.linkedlineconnect.navigation.ROUTE_CHATPAGE
import com.moses.linkedlineconnect.navigation.ROUTE_PARENTPROFILE
import com.moses.linkedlineconnect.navigation.ROUTE_TRACK_BUS

//import com.moses.linkedlineconnect.ui.theme.UsersParents.TrackBusScreen.TrackBusScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { /* TODO: Open Drawer */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Card: Your Students
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.elevatedCardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB))
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Your Students",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0D47A1)
                        )
                    }
                }

                // Buttons with icons
                Button(
                    onClick = { navController.navigate(ROUTE_BOOKINGPAGE) }, // Navigate to BookingPageScreen
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Icon(Icons.Filled.AddCircle, contentDescription = "Make a Booking", tint = Color.White)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Make a Booking", fontSize = 18.sp, color = Color.White)
                }

                Button(
                    onClick = { /* TODO: Navigate to View Trips */ }, // Placeholder for View Trips
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                ) {
                    Icon(Icons.Filled.ShoppingCart, contentDescription = "View Trips", tint = Color.White)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("View Trips", fontSize = 18.sp, color = Color.White)
                }

                Button(
                    onClick = { navController.navigate(ROUTE_CHATPAGE) }, // Navigate to ChatPageScreen
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Icon(Icons.Filled.MailOutline, contentDescription = "Chat Room", tint = Color.White)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Chat Room", fontSize = 18.sp, color = Color.White)
                }

                Button(
                    onClick = { navController.navigate(ROUTE_PARENTPROFILE) }, // Navigate to ParentProfileScreen
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
                ) {
                    Icon(Icons.Filled.Person, contentDescription = "Profile", tint = Color.White)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Profile", fontSize = 18.sp, color = Color.White)
                }

                Button(
                    onClick = { navController.navigate(ROUTE_TRACK_BUS) }, // Navigate to TrackBusScreen
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Icon(Icons.Filled.DirectionsBus, contentDescription = "Track Bus", tint = Color.White)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Track Bus", fontSize = 18.sp, color = Color.White)
                }
                Button(
                    onClick = { authViewModel.logout() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Logout", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    )
}

// @Preview
// @Composable
// fun DashBoardScreenPreview() {
//     DashBoardScreen(navController = NavHostController(LocalContext.current))
// }
