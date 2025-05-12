package com.moses.linkedlineconnect.ui.theme.AdminScreens.AdminDashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AdminDashboardScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var dialogContent by remember { mutableStateOf<@Composable () -> Unit>({}) }
    val db = FirebaseFirestore.getInstance()

    fun showPopup(content: @Composable () -> Unit) {
        dialogContent = content
        showDialog = true
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Close")
                }
            },
            text = {
                dialogContent()
            }
        )
    }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color.White,
                                Color(0xFF87CEEB), // Light Blue
                                Color.Black
                            ),
                            radius = 800f,
                            tileMode = TileMode.Mirror
                        )
                    )
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Admin Dashboard",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6200EE),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // 2 columns
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp), // Increased spacing between rows
                    horizontalArrangement = Arrangement.spacedBy(16.dp) // Increased spacing between columns
                ) {
                    item {
                        DashboardButton(
                            text = "Add/Edit Bus",
                            onClick = { showPopup { AddEditBusPopup(db) } }
                        )
                    }
                    item {
                        DashboardButton(
                            text = "Assign Escort",
                            onClick = { showPopup { AssignEscortPopup(db) } }
                        )
                    }
                    item {
                        DashboardButton(
                            text = "Manage Routes",
                            onClick = { showPopup { ManageRoutesPopup(db) } }
                        )
                    }
                    item {
                        DashboardButton(
                            text = "View Bookings",
                            onClick = { showPopup { ViewBookingsPopup() } }
                        )
                    }
                    item {
                        DashboardButton(
                            text = "Download Confirmed List",
                            onClick = { showPopup { DownloadConfirmedListPopup() } }
                        )
                    }
                    item {
                        DashboardButton(
                            text = "Send Announcement",
                            onClick = { showPopup { SendAnnouncementPopup(db) } }
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun DashboardButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp), // Larger button height
        shape = RoundedCornerShape(12.dp), // Rounded corners for a modern look
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.White,
                            Color(0xFF03A9F4) // Blue
                        )
                    )
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun AddEditBusPopup(db: FirebaseFirestore) {
    var busName by remember { mutableStateOf("") }

    Column {
        Text("Add/Edit Bus", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = busName,
            onValueChange = { busName = it },
            label = { Text("Bus Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (busName.isNotBlank()) {
                val busData = mapOf("name" to busName)
                db.collection("buses").add(busData)
            }
        }) {
            Text("Save")
        }
    }
}

@Composable
fun AssignEscortPopup(db: FirebaseFirestore) {
    var escortName by remember { mutableStateOf("") }

    Column {
        Text("Assign Escort", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = escortName,
            onValueChange = { escortName = it },
            label = { Text("Escort Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (escortName.isNotBlank()) {
                val escortData = mapOf("name" to escortName)
                db.collection("escorts").add(escortData)
            }
        }) {
            Text("Save")
        }
    }
}

@Composable
fun ManageRoutesPopup(db: FirebaseFirestore) {
    var routeName by remember { mutableStateOf("") }

    Column {
        Text("Manage Routes", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = routeName,
            onValueChange = { routeName = it },
            label = { Text("Route Name") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (routeName.isNotBlank()) {
                val routeData = mapOf("name" to routeName)
                db.collection("routes").add(routeData)
            }
        }) {
            Text("Save")
        }
    }
}

@Composable
fun ViewBookingsPopup() {
    Text("View Bookings functionality coming soon.")
}

@Composable
fun DownloadConfirmedListPopup() {
    Text("Download Confirmed List functionality coming soon.")
}

@Composable
fun SendAnnouncementPopup(db: FirebaseFirestore) {
    var announcement by remember { mutableStateOf("") }

    Column {
        Text("Send Announcement", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = announcement,
            onValueChange = { announcement = it },
            label = { Text("Announcement") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (announcement.isNotBlank()) {
                val announcementData = mapOf("message" to announcement)
                db.collection("announcements").add(announcementData)
            }
        }) {
            Text("Send")
        }
    }
}