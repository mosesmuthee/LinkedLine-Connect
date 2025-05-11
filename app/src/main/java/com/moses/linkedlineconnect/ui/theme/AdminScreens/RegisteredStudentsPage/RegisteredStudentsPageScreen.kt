package com.moses.linkedlineconnect.ui.theme.AdminScreens.RegisteredStudentsPage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Student(
    val name: String,
    val admissionNumber: String,
    val parentName: String,
    val parentContact: String,
    val paymentStatus: Boolean
)

@Composable
fun RegisteredStudentsPageScreen(
    students: List<Student>,
    onDownloadDetails: (Student) -> Unit,
    onDownloadAllDetails: () -> Unit
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
                    text = "Registered Students",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6200EE),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Download All Details Button
                Button(
                    onClick = onDownloadAllDetails,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                ) {
                    Text("Download All Details", fontSize = 16.sp, color = Color.White)
                }

                // Student List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(students) { student ->
                        StudentCard(student = student, onDownloadDetails = onDownloadDetails)
                    }
                }
            }
        }
    )
}

@Composable
fun StudentCard(
    student: Student,
    onDownloadDetails: (Student) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Student Name
            Text(
                text = "Name: ${student.name}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Admission Number
            Text(
                text = "Admission Number: ${student.admissionNumber}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            // Parent Name
            Text(
                text = "Parent: ${student.parentName}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            // Parent Contact
            Text(
                text = "Contact: ${student.parentContact}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            // Payment Status
            Text(
                text = "Payment Status: ${if (student.paymentStatus) "Paid" else "Not Paid"}",
                fontSize = 14.sp,
                color = if (student.paymentStatus) Color(0xFF4CAF50) else Color(0xFFF44336)
            )

            // Download Details Button
            Button(
                onClick = { onDownloadDetails(student) },
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("Download Details", fontSize = 14.sp, color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun RegisteredStudentsPageScreenPreview() {
    val sampleStudents = listOf(
        Student(
            name = "John Doe",
            admissionNumber = "ADM001",
            parentName = "Jane Doe",
            parentContact = "+123456789",
            paymentStatus = true
        ),
        Student(
            name = "Alice Smith",
            admissionNumber = "ADM002",
            parentName = "Robert Smith",
            parentContact = "+987654321",
            paymentStatus = false
        )
    )
    RegisteredStudentsPageScreen(
        students = sampleStudents,
        onDownloadDetails = {},
        onDownloadAllDetails = {}
    )
}

