package com.moses.linkedlineconnect.ui.theme.UsersParents.BookingPage

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.moses.linkedlineconnect.navigation.ROUTE_PAYMENTSCREEN

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingPageScreen(
    navController: NavHostController,
    students: List<String>,
    schools: List<String>,
    routes: List<String>
) {
    var studentName by remember { mutableStateOf("") }
    var schoolName by remember { mutableStateOf("") }
    var routeName by remember { mutableStateOf("") }
    var collectionPoint by remember { mutableStateOf("") }
    var tripType by remember { mutableStateOf("One-way") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

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
                Text(
                    text = "Book a Trip",
                    fontSize = 24.sp,
                    color = Color(0xFF001F54),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                TextField(
                    value = studentName,
                    onValueChange = { studentName = it },
                    label = { Text("Student Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = schoolName,
                    onValueChange = { schoolName = it },
                    label = { Text("School Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = routeName,
                    onValueChange = { routeName = it },
                    label = { Text("Route") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = collectionPoint,
                    onValueChange = { collectionPoint = it },
                    label = { Text("Collection Point / Drop-off Point") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Black,
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedIndicatorColor = Color(0xFF001F54),
                        unfocusedIndicatorColor = Color.Blue,
                        cursorColor = Color(0xFF001F54)
                    )
                )

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
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF001F54))
                        )
                        Text(text = "One-way", fontSize = 14.sp)

                        RadioButton(
                            selected = tripType == "Round trip",
                            onClick = { tripType = "Round trip" },
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF001F54))
                        )
                        Text(text = "Round trip", fontSize = 14.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (studentName.isBlank() || schoolName.isBlank() || routeName.isBlank() || collectionPoint.isBlank()) {
                            dialogMessage = "Please fill all fields before continuing."
                            showDialog = true
                        } else {
                            navController.navigate(ROUTE_PAYMENTSCREEN)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF001F54))
                ) {
                    Text("Continue to Payment", fontSize = 16.sp, color = Color.White)
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    },
                    title = { Text("Incomplete Booking") },
                    text = { Text(dialogMessage) }
                )
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
                    focusedContainerColor = Color.Black,
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.Gray,
                    cursorColor = Color(0xFF001F54)
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

//@Preview
//@Composable
//fun BookingPageScreenPreview() {
//    val sampleStudents = listOf("John Doe", "Jane Smith")
//    val sampleSchools = listOf("ABC High School", "XYZ Academy")
//    val sampleRoutes = listOf("Route 1", "Route 2")
//
//    BookingPageScreen(
//        navController = NavHostController(LocalContext.current),
//        students = sampleStudents,
//        schools = sampleSchools,
//        routes = sampleRoutes,
//        onContinueToPayment = { _, _, _, _, _ -> }
//    )
//}
