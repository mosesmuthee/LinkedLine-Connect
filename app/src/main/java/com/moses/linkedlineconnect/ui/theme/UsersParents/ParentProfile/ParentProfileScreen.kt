package com.moses.linkedlineconnect.ui.theme.UsersParents.ParentProfile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.moses.linkedlineconnect.R
import com.moses.linkedlineconnect.navigation.ROUTE_DASHBOARDParent
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentProfileScreen(
    navController:NavController,
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
    var parentId = String()
    var parentData by remember { mutableStateOf<ParentData?>(null) }
    var studentsData by remember { mutableStateOf<List<StudentProfile>>(emptyList()) }
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Fetch parent and student data from the database
    LaunchedEffect(parentId) {
        db.collection("parents").document(parentId).get().addOnSuccessListener { document ->
            if (document.exists()) {
                parentData = document.toObject(ParentData::class.java)
            }
        }
        db.collection("students").whereEqualTo("parentId", parentId).get().addOnSuccessListener { querySnapshot ->
            studentsData = querySnapshot.documents.mapNotNull { it.toObject(StudentProfile::class.java) }
        }
    }

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
//                    Text("Bus Pickup Screen", fontSize = 16.sp, modifier = Modifier.clickable { onNavigateToBusPickup() })
//                    Text("Chat Page Screen", fontSize = 16.sp, modifier = Modifier.clickable { onNavigateToChatPage() })
//                    Text("Payment Confirmation Screen", fontSize = 16.sp, modifier = Modifier.clickable { onNavigateToPaymentConfirmation() })
//                    Text("Payment Screen", fontSize = 16.sp, modifier = Modifier.clickable { onNavigateToPayment() })
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
                                ReadOnlyField("First Name", student.firstName)
                                ReadOnlyField("Last Name", student.lastName)
                                ReadOnlyField("Form", student.form)
                                ReadOnlyField("School", student.school)
                            }
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

@Composable
fun ReadOnlyField(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
        TextField(
            value = value,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            readOnly = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.LightGray,
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
    val parentId: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val form: String = "",
    val school: String = ""
)
