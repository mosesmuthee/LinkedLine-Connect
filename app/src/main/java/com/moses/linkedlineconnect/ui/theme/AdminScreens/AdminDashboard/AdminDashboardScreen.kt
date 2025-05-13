package com.moses.linkedlineconnect.ui.theme.AdminScreens.AdminDashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.moses.linkedlineconnect.data.AuthViewModel
import com.moses.linkedlineconnect.navigation.ROUTE_LOGINParent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
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
                        text = "Menu",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF87CEEB)
                    )
                    Divider(color = Color.Gray, thickness = 1.dp)
                    Text(
                        text = "Logout",
                        fontSize = 16.sp,
                        color = Color.Red,
                        modifier = Modifier.clickable {
                            authViewModel.logout()
                            navController.navigate(ROUTE_LOGINParent) // Navigate to the login screen
                        }
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Admin Dashboard", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF000080), // Dark Blue
                        titleContentColor = Color.White
                    ),
                    actions = {
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
                        }
                    }
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Black, Color(0xFF000080)) // Black to Dark Blue gradient
                            )
                        )
                        .padding(paddingValues)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // 2 columns
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
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
                        item {
                            DashboardButton(
                                text = "Logout",
                                onClick = {
                                    authViewModel.logout()
                                    navController.navigate(ROUTE_LOGINParent) // Navigate to the login screen
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun DashboardButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp), // Larger button height
        shape = RoundedCornerShape(12.dp), // Rounded corners for a modern look
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF000080)) // Dark Blue
    ) {
        Text(text, fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
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