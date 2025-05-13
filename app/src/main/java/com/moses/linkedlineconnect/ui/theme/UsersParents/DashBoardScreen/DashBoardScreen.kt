package com.moses.linkedlineconnect.ui.theme.UsersParents.DashBoardScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
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
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFF6200EE), Color(0xFF03A9F4))
                        )
                    )
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

                // Buttons inside gradient cards
                GradientCard(
                    gradientColors = listOf(Color(0xFF6200EE), Color(0xFF03A9F4)),
                    onClick = { navController.navigate(ROUTE_BOOKINGPAGE) }
                ) {
                    Icon(Icons.Filled.AddCircle, contentDescription = "Make a Booking", tint = Color.White)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Make a Booking", fontSize = 18.sp, color = Color.White)
                }

                GradientCard(
                    gradientColors = listOf(Color(0xFF4CAF50), Color(0xFF8BC34A)),
                    onClick = { navController.navigate(ROUTE_CHATPAGE) }
                ) {
                    Icon(Icons.Filled.MailOutline, contentDescription = "Chat Room", tint = Color.White)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Chat Room", fontSize = 18.sp, color = Color.White)
                }

                GradientCard(
                    gradientColors = listOf(Color(0xFFFF9800), Color(0xFFFFC107)),
                    onClick = { navController.navigate(ROUTE_PARENTPROFILE) }
                ) {
                    Icon(Icons.Filled.Person, contentDescription = "Profile", tint = Color.White)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Profile", fontSize = 18.sp, color = Color.White)
                }

                // Add Student Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clickable { navController.navigate(ROUTE_STUDENTREG) },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.elevatedCardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF6200EE))
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Add Student", tint = Color.White)
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("Add Student", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                GradientCard(
                    gradientColors = listOf(Color(0xFFFF5252), Color(0xFFFF1744)),
                    onClick = { authViewModel.logout() }
                ) {
                    Text("Logout", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    )
}

@Composable
fun GradientCard(
    gradientColors: List<Color>,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(colors = gradientColors)
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                content = content
            )
        }
    }
}
