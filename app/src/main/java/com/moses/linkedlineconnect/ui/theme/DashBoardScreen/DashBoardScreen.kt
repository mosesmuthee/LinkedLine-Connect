package com.moses.linkedlineconnect.ui.theme.DashBoardScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed) // Correct drawer state

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Navigation",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6200EE)
                    )
                    Divider(color = Color.LightGray, thickness = 1.dp)
                    Text(
                        text = "Dashboard",
                        fontSize = 16.sp,
                        modifier = Modifier.clickable { /* TODO: Navigate to Dashboard */ }
                    )
                    Text(
                        text = "Make a Booking",
                        fontSize = 16.sp,
                        modifier = Modifier.clickable { /* TODO: Navigate to Make a Booking */ }
                    )
                    Text(
                        text = "View Trips",
                        fontSize = 16.sp,
                        modifier = Modifier.clickable { /* TODO: Navigate to View Trips */ }
                    )
                    Text(
                        text = "Chat Room",
                        fontSize = 16.sp,
                        modifier = Modifier.clickable { /* TODO: Navigate to Chat Room */ }
                    )
                    Text(
                        text = "Profile / Settings",
                        fontSize = 16.sp,
                        modifier = Modifier.clickable { /* TODO: Navigate to Profile / Settings */ }
                    )
                }
            }
        },
        drawerState = drawerState // Use the correct drawer state
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
                        IconButton(onClick = { drawerState }) { // Open the drawer
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
                        onClick = { /* TODO: Navigate to Make a Booking */ },
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
                        onClick = { /* TODO: Navigate to View Trips */ },
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
                        onClick = { /* TODO: Navigate to Chat Room */ },
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
                        onClick = { /* TODO: Navigate to Profile / Settings */ },
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
                }
            }
        )
    }
}

@Preview
@Composable
fun DashBoardScreenPreview() {
    DashBoardScreen()
}
