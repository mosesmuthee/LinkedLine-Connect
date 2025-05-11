package com.moses.linkedlineconnect.ui.theme.UsersParents.BookingPage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun BookingPageScreen(
    navController: NavHostController,
    students: List<String>,
    schools: List<String>,
    routes: List<String>,
    onContinueToPayment: (String, String, String, String, String) -> Unit
) {
    var selectedStudent by remember { mutableStateOf("") }
    var selectedSchool by remember { mutableStateOf("") }
    var selectedRoute by remember { mutableStateOf("") }
    var collectionPoint by remember { mutableStateOf("") }
    var tripType by remember { mutableStateOf("One-way") }

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
                    text = "Book a Trip",
                    fontSize = 24.sp,
                    color = Color(0xFF6200EE),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Dropdown: Select Student
                DropdownField(
                    label = "Select Student",
                    options = students,
                    selectedOption = selectedStudent,
                    onOptionSelected = { selectedStudent = it }
                )

                // Dropdown: Select School
                DropdownField(
                    label = "Select School",
                    options = schools,
                    selectedOption = selectedSchool,
                    onOptionSelected = { selectedSchool = it }
                )

                // Dropdown: Select Route
                DropdownField(
                    label = "Select Route",
                    options = routes,
                    selectedOption = selectedRoute,
                    onOptionSelected = { selectedRoute = it }
                )

                // Input: Collection/Drop-off Point
                TextField(
                    value = collectionPoint,
                    onValueChange = { collectionPoint = it },
                    label = { Text("Collection Point / Drop-off Point") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedIndicatorColor = Color(0xFF6200EE),
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color(0xFF6200EE)
                    )
                )

                // RadioButton: Trip Type
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Trip Type",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        RadioButton(
                            selected = tripType == "One-way",
                            onClick = { tripType = "One-way" },
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF6200EE))
                        )
                        Text(text = "One-way", fontSize = 14.sp)

                        RadioButton(
                            selected = tripType == "Round trip",
                            onClick = { tripType = "Round trip" },
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF6200EE))
                        )
                        Text(text = "Round trip", fontSize = 14.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Continue to Payment Button
                Button(
                    onClick = {
                        onContinueToPayment(
                            selectedStudent,
                            selectedSchool,
                            selectedRoute,
                            collectionPoint,
                            tripType
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text("Continue to Payment", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    )
}

@Composable
fun DropdownField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Box {
            TextField(
                value = selectedOption,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                readOnly = true,
                label = { Text(label) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedIndicatorColor = Color(0xFF6200EE),
                    unfocusedIndicatorColor = Color.Gray,
                    cursorColor = Color(0xFF6200EE)
                )
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun BookingPageScreenPreview() {
    val sampleStudents = listOf("John Doe", "Jane Smith")
    val sampleSchools = listOf("ABC High School", "XYZ Academy")
    val sampleRoutes = listOf("Route 1", "Route 2")

    BookingPageScreen(
        navController = NavHostController(LocalContext.current),
        students = sampleStudents,
        schools = sampleSchools,
        routes = sampleRoutes,
        onContinueToPayment = { _, _, _, _, _ -> }
    )
}

