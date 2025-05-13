package com.moses.linkedlineconnect.ui.theme.UsersParents.StudentRegScreen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.moses.linkedlineconnect.R

@Composable
fun StudentRegScreen(
    navController: NavHostController // Use NavController for navigation
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var admissionNumber by remember { mutableStateOf("") }
    var selectedSchool by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var studentPhoto by remember { mutableIntStateOf(R.drawable.moses) }
    var termsAccepted by remember { mutableStateOf(false) }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = "Student Registration",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6200EE),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Profile Picture and Add New Student Button
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Upload Student Photo
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clickable {
                                // TODO: Implement photo upload logic
                                studentPhoto = R.drawable.moses
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = studentPhoto),
                            contentDescription = "Student Photo",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                        )
                    }
                    Text(
                        text = "Upload Photo",
                        fontSize = 14.sp,
                        color = Color(0xFF6200EE),
                        modifier = Modifier.clickable {
                            // TODO: Implement photo upload logic
                            studentPhoto = R.drawable.moses
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Add Another Student Button
                    Button(
                        onClick = { /* TODO: Add logic to add another student */ },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Add Student")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Add Another Student", fontSize = 16.sp, color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Student Details Box
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // First Name Input
                        TextField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            label = { Text("First Name") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFFF5F5F5),
                                unfocusedContainerColor = Color(0xFFF5F5F5),
                                focusedIndicatorColor = Color(0xFF6200EE),
                                unfocusedIndicatorColor = Color.Gray,
                                cursorColor = Color(0xFF6200EE)
                            )
                        )

                        // Last Name Input
                        TextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            label = { Text("Last Name") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFFF5F5F5),
                                unfocusedContainerColor = Color(0xFFF5F5F5),
                                focusedIndicatorColor = Color(0xFF6200EE),
                                unfocusedIndicatorColor = Color.Gray,
                                cursorColor = Color(0xFF6200EE)
                            )
                        )

                        // Admission Number Input
                        TextField(
                            value = admissionNumber,
                            onValueChange = { admissionNumber = it },
                            label = { Text("Admission Number") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFFF5F5F5),
                                unfocusedContainerColor = Color(0xFFF5F5F5),
                                focusedIndicatorColor = Color(0xFF6200EE),
                                unfocusedIndicatorColor = Color.Gray,
                                cursorColor = Color(0xFF6200EE)
                            )
                        )

                        // School Name Dropdown
                        Box {
                            TextField(
                                value = selectedSchool,
                                onValueChange = {},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { expanded = true },
                                readOnly = true,
                                label = { Text("Select School") },
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
                                DropdownMenuItem(
                                    text = { Text("ABC High School") },
                                    onClick = {
                                        selectedSchool = "ABC High School"
                                        expanded = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("XYZ Academy") },
                                    onClick = {
                                        selectedSchool = "XYZ Academy"
                                        expanded = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("PQR International") },
                                    onClick = {
                                        selectedSchool = "PQR International"
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Register Student Button
                Button(
                    onClick = { navController.navigate("dashboard") }, // Navigate back to the dashboard
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text("Register Student", fontSize = 18.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Terms and Conditions Checkbox
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = termsAccepted,
                        onCheckedChange = { termsAccepted = it },
                        colors = CheckboxDefaults.colors(checkedColor = Color(0xFF6200EE))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "I agree to the terms and conditions",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Login Text
                Text(
                    text = "Already registered? Login here",
                    fontSize = 16.sp,
                    color = Color(0xFF6200EE),
                    modifier = Modifier.clickable { navController.navigate("login") }, // Navigate to the login screen
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}

//@Preview
//@Composable
//fun StudentRegScreenPreview() {
//    StudentRegScreen(
//        navController = rememberNavController()
//    )
//}

