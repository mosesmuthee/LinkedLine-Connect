package com.moses.linkedlineconnect.ui.theme.UsersParents.ParentProfile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.moses.linkedlineconnect.R
import com.moses.linkedlineconnect.navigation.ROUTE_DASHBOARDParent
import com.moses.linkedlineconnect.navigation.ROUTE_LOGINParent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentProfileScreen(
    navController: NavController
) {
    // Parent fields
    var parentFirstName by remember { mutableStateOf("") }
    var parentLastName by remember { mutableStateOf("") }
    var parentEmail by remember { mutableStateOf("") }
    var parentPhone by remember { mutableStateOf("") }
    var parentImageUri by remember { mutableStateOf<Uri?>(null) }

    // Student fields
    var studentFirstName by remember { mutableStateOf("") }
    var studentLastName by remember { mutableStateOf("") }
    var studentImageUri by remember { mutableStateOf<Uri?>(null) }
    var studentAdmissionNumber by remember { mutableStateOf("") }
    var studentClass by remember { mutableStateOf("") }
    var studentSchool by remember { mutableStateOf("") }

    var showMenu by remember { mutableStateOf(false) }

    val parentImagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        parentImageUri = uri
    }
    val studentImagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        studentImageUri = uri
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                )
            )
            // Dropdown Menu
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Dashboard") },
                    onClick = {
                        showMenu = false
                        navController.navigate(ROUTE_DASHBOARDParent)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Logout", color = Color.Red) },
                    onClick = {
                        showMenu = false
                        FirebaseAuth.getInstance().signOut()
                        navController.navigate(ROUTE_LOGINParent) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Parent Section
            Text("Parent Details", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6200EE))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .clickable { parentImagePickerLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (parentImageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(parentImageUri),
                        contentDescription = "Parent Image",
                        modifier = Modifier.size(100.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.moses),
                        contentDescription = "Parent Placeholder",
                        modifier = Modifier.size(100.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            OutlinedTextField(
                value = parentFirstName,
                onValueChange = { parentFirstName = it },
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = parentLastName,
                onValueChange = { parentLastName = it },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = parentEmail,
                onValueChange = { parentEmail = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = parentPhone,
                onValueChange = { parentPhone = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )

            // Student Section
            Text("Student Details", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6200EE))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .clickable { studentImagePickerLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (studentImageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(studentImageUri),
                        contentDescription = "Student Image",
                        modifier = Modifier.size(100.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.moses),
                        contentDescription = "Student Placeholder",
                        modifier = Modifier.size(100.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            OutlinedTextField(
                value = studentFirstName,
                onValueChange = { studentFirstName = it },
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = studentLastName,
                onValueChange = { studentLastName = it },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = studentAdmissionNumber,
                onValueChange = { studentAdmissionNumber = it },
                label = { Text("Admission Number") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = studentClass,
                onValueChange = { studentClass = it },
                label = { Text("Class") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = studentSchool,
                onValueChange = { studentSchool = it },
                label = { Text("School") },
                modifier = Modifier.fillMaxWidth()
            )

            // Save Button
            Button(
                onClick = {
                    saveParentAndStudentToDatabase(
                        parentFirstName, parentLastName, parentEmail, parentPhone,
                        studentFirstName, studentLastName, studentAdmissionNumber, studentClass, studentSchool
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
            ) {
                Text("Save", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}

// Save logic to Firebase Realtime Database
fun saveParentAndStudentToDatabase(
    parentFirstName: String,
    parentLastName: String,
    parentEmail: String,
    parentPhone: String,
    studentFirstName: String,
    studentLastName: String,
    studentAdmissionNumber: String,
    studentClass: String,
    studentSchool: String
) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
    val db = FirebaseDatabase.getInstance().reference

    // Save parent
    val parentData = mapOf(
        "firstName" to parentFirstName,
        "lastName" to parentLastName,
        "email" to parentEmail,
        "phone" to parentPhone
    )
    db.child("Users").child(userId).child("Profile").setValue(parentData)

    // Save student
    val studentData = mapOf(
        "firstName" to studentFirstName,
        "lastName" to studentLastName,
        "admissionNumber" to studentAdmissionNumber,
        "class" to studentClass,
        "school" to studentSchool
    )
    db.child("Users").child(userId).child("Students").push().setValue(studentData)
}
