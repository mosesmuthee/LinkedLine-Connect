package com.moses.linkedlineconnect.ui.theme.UsersParents.StudentRegScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.moses.linkedlineconnect.R
import com.moses.linkedlineconnect.navigation.ROUTE_DASHBOARDParent

@Composable
fun StudentRegScreen(
    navController: NavHostController
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var admissionNumber by remember { mutableStateOf("") }
    var selectedSchool by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var studentClass by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var isCustomSchool by remember { mutableStateOf(false) } // Toggle between dropdown and manual input
    var termsAccepted by remember { mutableStateOf(false) }
    var isSaving by remember { mutableStateOf(false) }

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

                        // Email Input
                        TextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFFF5F5F5),
                                unfocusedContainerColor = Color(0xFFF5F5F5),
                                focusedIndicatorColor = Color(0xFF6200EE),
                                unfocusedIndicatorColor = Color.Gray,
                                cursorColor = Color(0xFF6200EE)
                            )
                        )

                        // Class Input
                        TextField(
                            value = studentClass,
                            onValueChange = { studentClass = it },
                            label = { Text("Class") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFFF5F5F5),
                                unfocusedContainerColor = Color(0xFFF5F5F5),
                                focusedIndicatorColor = Color(0xFF6200EE),
                                unfocusedIndicatorColor = Color.Gray,
                                cursorColor = Color(0xFF6200EE)
                            )
                        )

                        // School Name Input or Dropdown
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Checkbox(
                                    checked = isCustomSchool,
                                    onCheckedChange = { isCustomSchool = it },
                                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF6200EE))
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Enter school manually",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                            }

                            if (isCustomSchool) {
                                // Manual School Input
                                TextField(
                                    value = selectedSchool,
                                    onValueChange = { selectedSchool = it },
                                    label = { Text("School Name") },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color(0xFFF5F5F5),
                                        unfocusedContainerColor = Color(0xFFF5F5F5),
                                        focusedIndicatorColor = Color(0xFF6200EE),
                                        unfocusedIndicatorColor = Color.Gray,
                                        cursorColor = Color(0xFF6200EE)
                                    )
                                )
                            } else {
                                // Dropdown for School Selection
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
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Register Student Button
                Button(
                    onClick = {
                        saveStudentToDatabase(
                            firstName = firstName,
                            lastName = lastName,
                            admissionNumber = admissionNumber,
                            selectedSchool = selectedSchool,
                            email = email,
                            studentClass = studentClass,
                            onSuccess = {
                                navController.navigate(ROUTE_DASHBOARDParent) // Navigate back to the dashboard
                            },
                            onFailure = {
                                // Handle failure (e.g., show a toast)
                            }
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    if (isSaving) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
                    } else {
                        Text("Register Student", fontSize = 18.sp, color = Color.White)
                    }
                }
            }
        }
    )
}

fun saveStudentToDatabase(
    firstName: String,
    lastName: String,
    admissionNumber: String,
    selectedSchool: String,
    email: String,
    studentClass: String,
    onSuccess: () -> Unit,
    onFailure: () -> Unit
) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    if (userId == null) {
        onFailure()
        return
    }

    val studentData = mapOf(
        "firstName" to firstName,
        "lastName" to lastName,
        "admissionNumber" to admissionNumber,
        "school" to selectedSchool,
        "email" to email,
        "class" to studentClass
    )

    val databaseRef = FirebaseDatabase.getInstance().getReference("Users/$userId/Students")
    databaseRef.push().setValue(studentData).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            onSuccess()
        } else {
            onFailure()
        }
    }
}

