package com.moses.linkedlineconnect.ui.theme.UsersParents.ParentProfile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
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
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.FirebaseFirestore
import com.moses.linkedlineconnect.R
import com.moses.linkedlineconnect.navigation.ROUTE_BUSPICKUP
import com.moses.linkedlineconnect.navigation.ROUTE_CHATPAGE
import com.moses.linkedlineconnect.navigation.ROUTE_DASHBOARDParent
import com.moses.linkedlineconnect.navigation.ROUTE_PAYMENTCONFIRMATION
import com.moses.linkedlineconnect.navigation.ROUTE_PAYMENTSCREEN
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentProfileScreen(
    navController:NavController,
//    parentId: String
//    parentId: String, // Pass the parent ID to fetch data
//    onNavigateBack: () -> Unit,
//    onLogout: () -> Unit,
//    onNavigateToDashboard: () -> Unit,
//    onNavigateToBusPickup: () -> Unit,
//    onNavigateToChatPage: () -> Unit,
//    onNavigateToPaymentConfirmation: () -> Unit,
//    onNavigateToPayment: () -> Unit,
//    onNavigateToTrackBus: () -> Unit
) {
    val db = FirebaseFirestore.getInstance()
//    var parentId = String()
    var parentData by remember { mutableStateOf<ParentData?>(null) }
    var studentsData by remember { mutableStateOf<List<StudentProfile>>(emptyList()) }
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Fetch parent and student data from the database
//    LaunchedEffect(parentId) {
//        db.collection("parents").document(parentId).get().addOnSuccessListener { document ->
//            if (document.exists()) {
//                parentData = document.toObject(ParentData::class.java)
//            }
//        }
//        db.collection("students").whereEqualTo("parentId", parentId).get().addOnSuccessListener { querySnapshot ->
//            studentsData = querySnapshot.documents.mapNotNull { it.toObject(StudentProfile::class.java) }
//        }
//    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Navigation",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6200EE)
                    )
                    HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                    Text("Home Dashboard", fontSize = 16.sp, modifier = Modifier.clickable { navController.navigate(
                        ROUTE_DASHBOARDParent
                    ) })
                    Text("Bus Pickup Screen", fontSize = 16.sp, modifier = Modifier.clickable {navController.navigate(
                        ROUTE_BUSPICKUP
                    ) })
                    Text("Chat Page Screen", fontSize = 16.sp, modifier = Modifier.clickable { navController.navigate(
                        ROUTE_CHATPAGE
                    ) })
                    Text("Payment Confirmation Screen", fontSize = 16.sp, modifier = Modifier.clickable { navController.navigate(
                        ROUTE_PAYMENTCONFIRMATION
                    ) })
                    Text("Payment Screen", fontSize = 16.sp, modifier = Modifier.clickable { navController.navigate(
                        ROUTE_PAYMENTSCREEN
                    ) })
//                    Text("Track Bus Screen", fontSize = 16.sp, modifier = Modifier.clickable { onNavigateToTrackBus() })
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "LinkedLine Connect",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    },
                    actions = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
                        }
                        Text(
                            text = "Log Out",
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier
                                .padding(end = 16.dp)
//                                .clickable { onLogout() }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF6200EE),
                        titleContentColor = Color.White
                    )
                )
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        // Parent Details Section
                        Text(
                            text = "Parent Details",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF6200EE)
                        )
                        parentData?.let { parent ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                // Parent Image
                                var parentImageUri by remember { mutableStateOf<Uri?>(null) }
                                val parentImagePickerLauncher = rememberLauncherForActivityResult(
                                    contract = ActivityResultContracts.GetContent()
                                ) { uri: Uri? ->
                                    parentImageUri = uri
                                }

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
                                            modifier = Modifier
                                                .size(100.dp)
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop
                                        )
                                    } else {
                                        Image(
                                            painter = painterResource(id = R.drawable.moses), // Replace with a placeholder image
                                            contentDescription = "Parent Placeholder",
                                            modifier = Modifier
                                                .size(100.dp)
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }

                                EditableField("First Name", parent.firstName) { newValue ->
                                    parentData = parent.copy(firstName = newValue)
                                }
                                EditableField("Last Name", parent.lastName) { newValue ->
                                    parentData = parent.copy(lastName = newValue)
                                }
                                EditableField("Phone Number", parent.phoneNumber) { newValue ->
                                    parentData = parent.copy(phoneNumber = newValue)
                                }
                                EditableField("Email", parent.email) { newValue ->
                                    parentData = parent.copy(email = newValue)
                                }
                            }
                        }

                        // Students Details Section
                        Text(
                            text = "Students Details",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF6200EE)
                        )
                        studentsData.forEach { student ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                // Student Image
                                var studentImageUri by remember { mutableStateOf<Uri?>(null) }
                                val studentImagePickerLauncher = rememberLauncherForActivityResult(
                                    contract = ActivityResultContracts.GetContent()
                                ) { uri: Uri? ->
                                    studentImageUri = uri
                                }

                                Box(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(CircleShape)
                                        .clickable { studentImagePickerLauncher.launch("image/*") },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (studentImageUri != null) {
                                        Image(
                                            painter = rememberImagePainter(studentImageUri),
                                            contentDescription = "Student Image",
                                            modifier = Modifier
                                                .size(100.dp)
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop
                                        )
                                    } else {
                                        Image(
                                            painter = painterResource(id = R.drawable.moses), // Replace with a placeholder image
                                            contentDescription = "Student Placeholder",
                                            modifier = Modifier
                                                .size(100.dp)
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }

                                EditableField("First Name", student.firstName) { newValue ->
                                    studentsData = studentsData.toMutableList().apply {
                                        this[studentsData.indexOf(student)] = student.copy(firstName = newValue)
                                    }
                                }
                                EditableField("Last Name", student.lastName) { newValue ->
                                    studentsData = studentsData.toMutableList().apply {
                                        this[studentsData.indexOf(student)] = student.copy(lastName = newValue)
                                    }
                                }
                                EditableField("Form", student.form) { newValue ->
                                    studentsData = studentsData.toMutableList().apply {
                                        this[studentsData.indexOf(student)] = student.copy(form = newValue)
                                    }
                                }
                                EditableField("School", student.school) { newValue ->
                                    studentsData = studentsData.toMutableList().apply {
                                        this[studentsData.indexOf(student)] = student.copy(school = newValue)
                                    }
                                }
                            }
                        }

                        // Save Button
                        Button(
                            onClick = {
                                // Save parent and student data to the database
//                                db.collection("parents").document(parentId).set(parentData)
//                                    .addOnSuccessListener {
//                                        snackbarHostState.showSnackbar("Parent data updated successfully")
//                                    }
//                                    .addOnFailureListener { e ->
//                                        snackbarHostState.showSnackbar("Error updating parent data: ${e.message}")
//                                    }
//
//                                studentsData.forEach { student ->
//                                    db.collection("students").document(student.id).set(student)
//                                        .addOnSuccessListener {
//                                            snackbarHostState.showSnackbar("Student data updated successfully")
//                                        }
//                                        .addOnFailureListener { e ->
//                                            snackbarHostState.showSnackbar("Error updating student data: ${e.message}")
//                                        }
//                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                        ) {
                            Text("Save", fontSize = 16.sp, color = Color.White)
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun EditableField(label: String, value: String, onEdit: (String) -> Unit) {
    var textState by remember { mutableStateOf(value) }
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
        TextField(
            value = textState,
            onValueChange = {
                textState = it
                onEdit(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.LightGray
            )
        )
    }
}

data class ParentData(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val email: String = ""
)

data class StudentProfile(
    val firstName: String = "",
    val lastName: String = "",
    val form: String = "",
    val school: String = ""
)
