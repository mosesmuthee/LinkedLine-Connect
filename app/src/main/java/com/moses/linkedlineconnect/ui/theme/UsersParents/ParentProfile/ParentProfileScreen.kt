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
import com.moses.linkedlineconnect.R
import kotlinx.coroutines.launch

data class StudentProfile(
    var imageRes: Int,
    var firstName: String,
    var lastName: String,
    var form: String,
    var school: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentProfileScreen(
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit,
    onNavigateToDashboard: () -> Unit,
    onNavigateToBusPickup: () -> Unit,
    onNavigateToChatPage: () -> Unit,
    onNavigateToPaymentConfirmation: () -> Unit,
    onNavigateToPayment: () -> Unit,
    onNavigateToTrackBus: () -> Unit
) {
    var parentProfilePicture by remember { mutableStateOf(R.drawable.moses) }
    var students by remember {
        mutableStateOf(
            listOf(
                StudentProfile(R.drawable.moses, "Alice", "Brown", "Form 1", "ABC High School"),
                StudentProfile(R.drawable.moses, "Bob", "Smith", "Form 2", "ABC High School")
            )
        )
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

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
                    Text("Home Dashboard", fontSize = 16.sp, modifier = Modifier.clickable { onNavigateToDashboard() })
                    Text("Bus Pickup Screen", fontSize = 16.sp, modifier = Modifier.clickable { onNavigateToBusPickup() })
                    Text("Chat Page Screen", fontSize = 16.sp, modifier = Modifier.clickable { onNavigateToChatPage() })
                    Text("Payment Confirmation Screen", fontSize = 16.sp, modifier = Modifier.clickable { onNavigateToPaymentConfirmation() })
                    Text("Payment Screen", fontSize = 16.sp, modifier = Modifier.clickable { onNavigateToPayment() })
                    Text("Track Bus Screen", fontSize = 16.sp, modifier = Modifier.clickable { onNavigateToTrackBus() })
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
                                .clickable { onLogout() }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF6200EE),
                        titleContentColor = Color.White
                    )
                )
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            floatingActionButton = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(end = 16.dp, bottom = 16.dp)
                ) {
                    // Floating Button for Parent Details
                    FloatingActionButton(
                        onClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Parent details saved successfully!")
                            }
                        },
                        containerColor = Color(0xFF6200EE),
                        contentColor = Color.White
                    ) {
                        Icon(Icons.Filled.Save, contentDescription = "Save Parent Details")
                    }

                    // Floating Button for Student Details
                    FloatingActionButton(
                        onClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Student details saved successfully!")
                            }
                        },
                        containerColor = Color(0xFF03A9F4),
                        contentColor = Color.White
                    ) {
                        Icon(Icons.Filled.Save, contentDescription = "Save Student Details")
                    }
                }
            },
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
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clickable {
                                        parentProfilePicture = R.drawable.moses // TODO: replace with image picker
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Parent profile picture updated successfully!")
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = parentProfilePicture),
                                    contentDescription = "Parent Profile Picture",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                EditableField("First Name", "John") {}
                                EditableField("Last Name", "Doe") {}
                                EditableField("Phone Number", "+123456789") {}
                                EditableField("Email", "john.doe@example.com") {}
                            }
                        }

                        // Students Details Section
                        Text(
                            text = "Students Details",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF6200EE)
                        )
                        students.forEachIndexed { index, student ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clickable {
                                            val updatedList = students.toMutableList()
                                            updatedList[index] = updatedList[index].copy(imageRes = R.drawable.moses)
                                            students = updatedList
                                            coroutineScope.launch {
                                                snackbarHostState.showSnackbar("Student ${student.firstName}'s profile picture updated successfully!")
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = student.imageRes),
                                        contentDescription = "Student ${student.firstName} Profile Picture",
                                        modifier = Modifier
                                            .size(100.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    EditableField("First Name", student.firstName) {}
                                    EditableField("Last Name", student.lastName) {}
                                    EditableField("Form", student.form) {}
                                    EditableField("School", student.school) {}
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun EditableField(label: String, value: String, onEdit: () -> Unit) {
    var textState by remember { mutableStateOf(value) }
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
        TextField(
            value = textState,
            onValueChange = {
                textState = it
                onEdit()
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

@Preview
@Composable
fun ParentProfileScreenPreview() {
    ParentProfileScreen(
        onNavigateBack = {},
        onLogout = {},
        onNavigateToDashboard = {},
        onNavigateToBusPickup = {},
        onNavigateToChatPage = {},
        onNavigateToPaymentConfirmation = {},
        onNavigateToPayment = {},
        onNavigateToTrackBus = {}
    )
}
