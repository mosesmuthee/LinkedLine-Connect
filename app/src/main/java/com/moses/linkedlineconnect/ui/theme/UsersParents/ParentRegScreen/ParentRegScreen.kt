//package com.moses.linkedlineconnect.ui.theme.UsersParents.ParentRegScreen
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.text.style.TextDecoration
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.moses.linkedlineconnect.R
//import com.moses.linkedlineconnect.data.AuthViewModel
////import com.moses.linkedlineconnect.data.BiometricAuthHelper
//
//@RequiresApi(Build.VERSION_CODES.R)
//@Composable
//fun ParentRegScreen(
//    navController: NavHostController,
//    onLoginClick: () -> Unit
//) {
//    val context = LocalContext.current
//    val authViewModel = remember { AuthViewModel(navController, context) }
////    val biometricAuthHelper = remember { BiometricAuthHelper(context as androidx.fragment.app.FragmentActivity) }
//
//    var firstName by remember { mutableStateOf("") }
//    var lastName by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var confirmPassword by remember { mutableStateOf("") }
//    var showPassword by remember { mutableStateOf(false) }
//    var termsAccepted by remember { mutableStateOf(false) }
//
//    Scaffold(
//        content = { paddingValues ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(paddingValues)
//                    .padding(16.dp)
//                    .verticalScroll(rememberScrollState()),
//                verticalArrangement = Arrangement.spacedBy(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                // Profile Picture Section
//                Box(
//                    modifier = Modifier
//                        .size(100.dp)
//                        .clickable { /* TODO: Add profile picture upload logic */ },
//                    contentAlignment = Alignment.Center
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.moses),
//                        contentDescription = "Profile Picture",
//                        modifier = Modifier
//                            .size(100.dp)
//                            .clip(CircleShape),
//                        contentScale = ContentScale.Crop
//                    )
//                }
//                Text(
//                    text = "Tap to add profile picture (optional)",
//                    fontSize = 12.sp,
//                    color = Color.Gray
//                )
//
//                // Title
//                Text(
//                    text = "Register Parent",
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = MaterialTheme.colorScheme.primary
//                )
//
//                // First Name
//                InputField(
//                    label = "First Name *",
//                    value = firstName,
//                    onValueChange = { firstName = it }
//                )
//
//                // Last Name
//                InputField(
//                    label = "Last Name *",
//                    value = lastName,
//                    onValueChange = { lastName = it }
//                )
//
//                // Email
//                InputField(
//                    label = "Email *",
//                    value = email,
//                    onValueChange = { email = it }
//                )
//
//                // Password
//                PasswordField(
//                    label = "Password *",
//                    password = password,
//                    onPasswordChange = { password = it },
//                    showPassword = showPassword,
//                    onTogglePasswordVisibility = { showPassword = !showPassword }
//                )
//
//                // Confirm Password
//                PasswordField(
//                    label = "Confirm Password *",
//                    password = confirmPassword,
//                    onPasswordChange = { confirmPassword = it },
//                    showPassword = showPassword,
//                    onTogglePasswordVisibility = { showPassword = !showPassword }
//                )
//
//                // Terms and Conditions Checkbox
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Checkbox(
//                        checked = termsAccepted,
//                        onCheckedChange = { termsAccepted = it }
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "I agree to the terms and conditions *",
//                        fontSize = 14.sp,
//                        color = Color.Gray
//                    )
//                }
//
//                // Register Button
//                Button(
//                    onClick = {
//                        if (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank() &&
//                            password == confirmPassword && termsAccepted
//                        ) {
//                            authViewModel.signup(
//                                                                email = email,
//                                idNumber = "", // TODO: Add ID number field
//                                phoneNumber = "", // TODO: Add phone number field
//                                password = password,
//                                confirmPassword = confirmPassword,
//                                role = "Parent",
//                                lastName = lastName
//                            )
//                        } else {
//                            // TODO: Show validation error
//                        }
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
//                ) {
//                    Text("Register", fontSize = 16.sp, color = Color.White)
//                }
//
//                // Enable Biometric Authentication Button
////                Button(
////                    onClick = {
////                        biometricAuthHelper.biometricLogin(
////                            onSuccess = {
////                                // TODO: Save biometric preference for the user
////                            },
////                            onFailure = {
////                                // TODO: Handle failure (e.g., show a toast or error message)
////                            }
////                        )
////                    },
////                    modifier = Modifier.fillMaxWidth(),
////                    shape = RoundedCornerShape(12.dp),
////                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
////                ) {
////                    Text("Enable Biometric Authentication", fontSize = 16.sp, color = Color.White)
////                }
//
//                // Login Text
//                Text(
//                    text = "Not new here? Login",
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = MaterialTheme.colorScheme.primary,
//                    textDecoration = TextDecoration.Underline,
//                    modifier = Modifier.clickable { onLoginClick() }
//                )
//            }
//        }
//    )
//}
//
//@Composable
//fun InputField(label: String, value: String, onValueChange: (String) -> Unit) {
//    Column {
//        Text(
//            text = label,
//            fontSize = 14.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Gray
//        )
//        TextField(
//            value = value,
//            onValueChange = onValueChange,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(56.dp),
//            shape = RoundedCornerShape(8.dp),
//            colors = TextFieldDefaults.colors(
//                focusedContainerColor = Color.White,
//                unfocusedContainerColor = Color.White,
//                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
//                unfocusedIndicatorColor = Color.LightGray
//            )
//        )
//    }
//}
//
//@Composable
//fun PasswordField(
//    label: String,
//    password: String,
//    onPasswordChange: (String) -> Unit,
//    showPassword: Boolean,
//    onTogglePasswordVisibility: () -> Unit
//) {
//    Column {
//        Text(
//            text = label,
//            fontSize = 14.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Gray
//        )
//        TextField(
//            value = password,
//            onValueChange = onPasswordChange,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(56.dp),
//            shape = RoundedCornerShape(8.dp),
//            colors = TextFieldDefaults.colors(
//                focusedContainerColor = Color.White,
//                unfocusedContainerColor = Color.White,
//                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
//                unfocusedIndicatorColor = Color.LightGray
//            ),
//            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
//            trailingIcon = {
//                IconButton(onClick = onTogglePasswordVisibility) {
//                    Icon(
//                        imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
//                        contentDescription = "Toggle Password Visibility"
//                    )
//                }
//            }
//        )
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.R)
//@Preview
//@Composable
//fun ParentRegScreenPreview() {
//    rememberNavController()
//    ParentRegScreen(navController = NavHostController(LocalContext.current),
//        onLoginClick = {})
//}
//
