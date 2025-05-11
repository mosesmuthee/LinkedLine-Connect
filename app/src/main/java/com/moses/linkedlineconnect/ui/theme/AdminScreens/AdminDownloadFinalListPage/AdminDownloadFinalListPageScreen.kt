package com.moses.linkedlineconnect.ui.theme.AdminScreens.AdminDownloadFinalListPage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdminDownloadFinalListPageScreen(
    onGenerateList: () -> Unit,
    onNavigateToStudentDetails: () -> Unit
) {
    var selectedSchool by remember { mutableStateOf("") }
    var selectedRoute by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }

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
                    text = "Download Confirmed List",
                    fontSize = 24.sp,
                    color = Color(0xFF6200EE),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Filter: School
                TextField(
                    value = selectedSchool,
                    onValueChange = { selectedSchool = it },
                    label = { Text("School") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedIndicatorColor = Color(0xFF6200EE),
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color(0xFF6200EE)
                    )
                )

                // Filter: Route
                TextField(
                    value = selectedRoute,
                    onValueChange = { selectedRoute = it },
                    label = { Text("Route") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedIndicatorColor = Color(0xFF6200EE),
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color(0xFF6200EE)
                    )
                )

                // Filter: Date
                TextField(
                    value = selectedDate,
                    onValueChange = { selectedDate = it },
                    label = { Text("Date") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedIndicatorColor = Color(0xFF6200EE),
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color(0xFF6200EE)
                    )
                )

                // Generate List Button
                Button(
                    onClick = onGenerateList,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text("Generate List", fontSize = 16.sp, color = Color.White)
                }

                // Navigate to Student Details Page
                Button(
                    onClick = onNavigateToStudentDetails,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                ) {
                    Text("View Student Details", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    )
}

@Preview
@Composable
fun AdminDownloadFinalListPageScreenPreview() {
    AdminDownloadFinalListPageScreen(
        onGenerateList = {},
        onNavigateToStudentDetails = {}
    )
}

