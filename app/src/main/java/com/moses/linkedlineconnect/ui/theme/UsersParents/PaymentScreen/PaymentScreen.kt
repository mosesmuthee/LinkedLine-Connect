package com.moses.linkedlineconnect.ui.theme.PaymentScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore
import com.moses.linkedlineconnect.R

@Composable
fun MpesaPaymentScreen(
    studentId: String, // Pass the student ID to fetch details
    onPay: (String) -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    var studentName by remember { mutableStateOf("") }
    var routeName by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(true) }
    var phoneNumber by remember { mutableStateOf("") }

    // Fetch student details from Firestore
    LaunchedEffect(studentId) {
        db.collection("registeredStudents")
            .document(studentId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    studentName = document.getString("name") ?: "Unknown"
                    routeName = document.getString("route") ?: "Unknown"
                }
                loading = false
            }
            .addOnFailureListener {
                loading = false
            }
    }

    Scaffold(
        content = { paddingValues ->
            if (loading) {
                // Show loading indicator while fetching data
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF34B233))
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Mpesa Logo
                    Image(
                        painter = painterResource(id = R.drawable.moses), // Replace with your Mpesa logo resource
                        contentDescription = "Mpesa Logo",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(bottom = 16.dp)
                    )

                    // Title
                    Text(
                        text = "Pay with Mpesa",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF34B233), // Mpesa green color
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Child Name and Route
                    Text(
                        text = "Child: $studentName",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Text(
                        text = "Route: $routeName",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Phone Number Input
                    TextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = { Text("Enter Phone Number") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            focusedIndicatorColor = Color(0xFF6200EE),
                            unfocusedIndicatorColor = Color.Gray,
                            cursorColor = Color(0xFF6200EE)
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Pay Button
                    Button(
                        onClick = { onPay(phoneNumber) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF34B233))
                    ) {
                        Text("Pay", fontSize = 16.sp, color = Color.White)
                    }
                }
            }
        }
    )
}

//@Preview
//@Composable
//fun MpesaPaymentScreenPreview() {
//    MpesaPaymentScreen(
//        studentId = "sampleStudentId",
//        onPay = { phoneNumber ->
//            // Handle payment logic here
//        }
//    )
//}

