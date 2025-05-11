package com.moses.linkedlineconnect.ui.theme.UsersParents.StudentDetailsPage

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
fun StudentDetailsPageScreen(
    onDownloadDetails: () -> Unit
) {
    var studentName by remember { mutableStateOf("John Doe") }
    var parentName by remember { mutableStateOf("Jane Doe") }
    var parentContact by remember { mutableStateOf("+123456789") }

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
                    text = "Student Details",
                    fontSize = 24.sp,
                    color = Color(0xFF6200EE),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Student Name
                TextField(
                    value = studentName,
                    onValueChange = {},
                    label = { Text("Student Name") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedIndicatorColor = Color(0xFF6200EE),
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color(0xFF6200EE)
                    )
                )

                // Parent Name
                TextField(
                    value = parentName,
                    onValueChange = {},
                    label = { Text("Parent Name") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedIndicatorColor = Color(0xFF6200EE),
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color(0xFF6200EE)
                    )
                )

                // Parent Contact
                TextField(
                    value = parentContact,
                    onValueChange = {},
                    label = { Text("Parent Contact") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedIndicatorColor = Color(0xFF6200EE),
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color(0xFF6200EE)
                    )
                )

                // Download Details Button
                Button(
                    onClick = onDownloadDetails,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text("Download Details", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    )
}

@Preview
@Composable
fun StudentDetailsPageScreenPreview() {
    StudentDetailsPageScreen(
        onDownloadDetails = {}
    )
}

