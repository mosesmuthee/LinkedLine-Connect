package com.moses.linkedlineconnect.ui.theme.UsersParents.DashBoardScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.navigation.NavHostController
import com.moses.linkedlineconnect.data.AuthViewModel
import com.moses.linkedlineconnect.navigation.ROUTE_BOOKINGPAGE
import com.moses.linkedlineconnect.navigation.ROUTE_CHATPAGE
import com.moses.linkedlineconnect.navigation.ROUTE_PARENTPROFILE
import com.moses.linkedlineconnect.navigation.ROUTE_STUDENTREG

private val NavyBlue = Color(0xFF001F54)
private val NavyBlueDark = Color(0xFF001233)
private val White = Color(0xFFFFFFFF)
private val Black = Color(0xFF111111)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyBlue,
                    titleContentColor = White
                ),
                actions = {
                    Box {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = White)
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Logout", color = Color.Red) },
                                onClick = {
                                    showMenu = false
                                    authViewModel.logout()
                                    navController.navigate(com.moses.linkedlineconnect.navigation.ROUTE_LOGINParent) {
                                        popUpTo(0) { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(NavyBlue, NavyBlueDark)
                        )
                    )
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Card: Your Students
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clickable { navController.navigate(ROUTE_PARENTPROFILE) },
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.elevatedCardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Your Students",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = NavyBlue
                            )
                            if (authViewModel.studentList.isEmpty()) {
                                Text(
                                    text = "No students registered yet",
                                    fontSize = 14.sp,
                                    color = Black
                                )
                            } else {
                                authViewModel.studentList.take(2).forEach { student ->
                                    Text(
                                        text = student.name,
                                        fontSize = 16.sp,
                                        color = NavyBlue
                                    )
                                    Text(
                                        text = student.admissionNumber,
                                        fontSize = 14.sp,
                                        color = Black
                                    )
                                }
                                if (authViewModel.studentList.size > 2) {
                                    Text(
                                        text = "View all...",
                                        fontSize = 14.sp,
                                        color = NavyBlue,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }
                }

                // Booking Button
                item {
                    DashboardButton(
                        text = "Make a Booking",
                        icon = Icons.Filled.AddCircle,
                        onClick = { navController.navigate(ROUTE_BOOKINGPAGE) }
                    )
                }

                // Chat Button
                item {
                    DashboardButton(
                        text = "Chat Room",
                        icon = Icons.Filled.MailOutline,
                        onClick = { navController.navigate(ROUTE_CHATPAGE) }
                    )
                }

                // Profile Button
                item {
                    DashboardButton(
                        text = "Profile",
                        icon = Icons.Filled.Person,
                        onClick = { navController.navigate(ROUTE_PARENTPROFILE) }
                    )
                }

                // Add Student Card
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .clickable { navController.navigate(ROUTE_STUDENTREG) },
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.elevatedCardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = NavyBlue)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(Icons.Filled.Add, contentDescription = "Add Student", tint = White)
                                Spacer(modifier = Modifier.width(12.dp))
                                Text("Add Student", fontSize = 18.sp, color = White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                // Logout Button
                item {
                    DashboardButton(
                        text = "Logout",
                        icon = Icons.Filled.ExitToApp,
                        onClick = { authViewModel.logout() },
                        backgroundColor = Black,
                        contentColor = White
                    )
                }
            }
        }
    )
}

@Composable
fun DashboardButton(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    backgroundColor: Color = NavyBlue,
    contentColor: Color = White
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
    ) {
        Icon(icon, contentDescription = text, tint = contentColor)
        Spacer(modifier = Modifier.width(12.dp))
        Text(text, fontSize = 18.sp, color = contentColor, fontWeight = FontWeight.Bold)
    }
}
