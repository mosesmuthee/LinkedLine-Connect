package com.moses.linkedlineconnect.ui.theme.AdminScreens.AdminDashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdminDashboardScreen(
    onAddEditBus: () -> Unit,
    onAssignEscort: () -> Unit,
    onManageRoutes: () -> Unit,
    onViewBookings: () -> Unit,
    onDownloadConfirmedList: () -> Unit,
    onSendAnnouncement: () -> Unit
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
                    text = "Admin Dashboard",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6200EE),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Purpose
                Text(
                    text = "Manage all activities",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Add/Edit Bus Button
                Button(
                    onClick = onAddEditBus,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                ) {
                    Text("Add/Edit Bus", fontSize = 16.sp, color = Color.White)
                }

                // Assign Escort Button
                Button(
                    onClick = onAssignEscort,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                ) {
                    Text("Assign Escort", fontSize = 16.sp, color = Color.White)
                }

                // Manage Routes Button
                Button(
                    onClick = onManageRoutes,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                ) {
                    Text("Manage Routes", fontSize = 16.sp, color = Color.White)
                }

                // View Bookings Button
                Button(
                    onClick = onViewBookings,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                ) {
                    Text("View Bookings", fontSize = 16.sp, color = Color.White)
                }

                // Download Confirmed List Button
                Button(
                    onClick = onDownloadConfirmedList,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                ) {
                    Text("Download Confirmed List (PDF/Excel)", fontSize = 16.sp, color = Color.White)
                }

                // Send Announcement Button
                Button(
                    onClick = onSendAnnouncement,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                ) {
                    Text("Send Announcement", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    )
}

@Preview
@Composable
fun AdminDashboardScreenPreview() {
    AdminDashboardScreen(
        onAddEditBus = {},
        onAssignEscort = {},
        onManageRoutes = {},
        onViewBookings = {},
        onDownloadConfirmedList = {},
        onSendAnnouncement = {}
    )
}

