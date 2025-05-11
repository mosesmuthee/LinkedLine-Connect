package com.moses.linkedlineconnect.ui.theme.BusPickupScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun BusPickupDialog(
    studentId: String, // ID of the student to fetch data for
    parentId: String, // ID of the parent to fetch data for
    onDismiss: () -> Unit,
    onUpdateStatus: (Boolean, Boolean) -> Unit // For escort to update pickup/drop-off status
) {
    val db = FirebaseFirestore.getInstance()
    var parentName by remember { mutableStateOf("") }
    var studentName by remember { mutableStateOf("") }
    var pickupPoint by remember { mutableStateOf("") }
    var dropOffPoint by remember { mutableStateOf("") }
    var isPickedUp by remember { mutableStateOf(false) }
    var isDroppedOff by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(true) }

    // Fetch parent and student data from Firestore
    LaunchedEffect(studentId, parentId) {
        loading = true
        db.collection("parents")
            .document(parentId)
            .get()
            .addOnSuccessListener { document ->
                parentName = document.getString("name") ?: "Unknown Parent"
            }

        db.collection("students")
            .document(studentId)
            .get()
            .addOnSuccessListener { document ->
                studentName = document.getString("name") ?: "Unknown Student"
                pickupPoint = document.getString("pickupPoint") ?: "Not Set"
                dropOffPoint = document.getString("dropOffPoint") ?: "Not Set"
            }
            .addOnCompleteListener {
                loading = false
            }
    }

    if (loading) {
        // Show loading indicator while fetching data
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(0xFF6200EE))
        }
    } else {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                Button(
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text("Close", color = Color.White)
                }
            },
            title = {
                Text(
                    text = "Welcome, $parentName",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6200EE)
                )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    // Student Name
                    Text(
                        text = "Student: $studentName",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    // Pickup Point
                    Text(
                        text = "Pickup Point: $pickupPoint",
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    // Drop-off Point
                    Text(
                        text = "Drop-off Point: $dropOffPoint",
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    // Escort Status Updates
                    Text(
                        text = "Escort Updates",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Checkbox(
                            checked = isPickedUp,
                            onCheckedChange = {
                                isPickedUp = it
                                onUpdateStatus(isPickedUp, isDroppedOff)
                            }
                        )
                        Text(text = "Picked Up", fontSize = 14.sp)

                        Checkbox(
                            checked = isDroppedOff,
                            onCheckedChange = {
                                isDroppedOff = it
                                onUpdateStatus(isPickedUp, isDroppedOff)
                            }
                        )
                        Text(text = "Dropped Off", fontSize = 14.sp)
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun BusPickupDialogPreview() {
    BusPickupDialog(
        studentId = "sampleStudentId",
        parentId = "sampleParentId",
        onDismiss = { /* Handle dismiss */ },
        onUpdateStatus = { pickedUp, droppedOff ->
            // Handle status updates
        }
    )
}

