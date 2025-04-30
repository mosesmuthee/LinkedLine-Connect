package com.moses.linkedlineconnect.ui.theme.WelcomePage

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomePageScreen(
    onNavigateToParentReg: () -> Unit,
    onNavigateToAdminReg: () -> Unit,
    onNavigateToEscortReg: () -> Unit
) {
    var selectedRole by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF03A9F4), // Light Blue
                        Color(0xFF6200EE)  // Purple-Blue
                    )
                )
            )
    ) {
        // Live Wallpaper Background
        LiveWallpaperBackground()

        // Foreground Content
        Scaffold(
            containerColor = Color.Transparent, // Make the scaffold background transparent
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    // Floating Box for Input Area
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .wrapContentHeight(),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Title
                            Text(
                                text = "Welcome to LinkedLine Connect",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6200EE),
                                modifier = Modifier.padding(bottom = 24.dp)
                            )

                            // Dropdown Menu for Role Selection
                            Text(
                                text = "Select Your Role",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6200EE),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Box {
                                TextField(
                                    value = selectedRole,
                                    onValueChange = {},
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { expanded = true },
                                    readOnly = true,
                                    label = { Text("Choose Role") },
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                                        unfocusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                                        unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface
                                    )
                                )
                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    DropdownMenuItem(
                                        text = { Text("Parent") },
                                        onClick = {
                                            selectedRole = "Parent"
                                            expanded = false
                                        }
                                    )
                                    DropdownMenuItem(
                                        text = { Text("Admin") },
                                        onClick = {
                                            selectedRole = "Admin"
                                            expanded = false
                                        }
                                    )
                                    DropdownMenuItem(
                                        text = { Text("Escort") },
                                        onClick = {
                                            selectedRole = "Escort"
                                            expanded = false
                                        }
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            // Buttons to Navigate to Specific Pages
                            Button(
                                onClick = {
                                    when (selectedRole) {
                                        "Parent" -> onNavigateToParentReg()
                                        "Admin" -> onNavigateToAdminReg()
                                        "Escort" -> onNavigateToEscortReg()
                                        else -> {} // Handle invalid selection if needed
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(),
                                enabled = selectedRole.isNotEmpty(),
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Text("Proceed to Registration", fontSize = 16.sp)
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun LiveWallpaperBackground() {
    val infiniteTransition = rememberInfiniteTransition()
    val offsetX by infiniteTransition.animateFloat(
        initialValue = -200f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val offsetY by infiniteTransition.animateFloat(
        initialValue = -200f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawIntoCanvas { canvas ->
            // Draw LinkedLine initials or abstract shapes
            drawCircle(
                color = Color(0xFF6200EE).copy(alpha = 0.2f),
                radius = 150f,
                center = Offset(size.width / 2 + offsetX, size.height / 2 + offsetY)
            )
            drawCircle(
                color = Color(0xFF03A9F4).copy(alpha = 0.2f),
                radius = 100f,
                center = Offset(size.width / 2 - offsetX, size.height / 2 - offsetY)
            )
        }
    }
}

@Preview
@Composable
fun WelcomePageScreenPreview() {
    WelcomePageScreen(
        onNavigateToParentReg = {},
        onNavigateToAdminReg = {},
        onNavigateToEscortReg = {}
    )
}
