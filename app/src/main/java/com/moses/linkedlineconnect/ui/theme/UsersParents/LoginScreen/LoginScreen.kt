package com.moses.linkedlineconnect.ui.theme.UsersParents.LoginScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.moses.linkedlineconnect.data.AuthViewModel

//import com.moses.linkedlineconnect.data.BiometricAuthHelper

@Composable
fun LoginScreen(
    navController: NavHostController,
    onNavigateToSignUp: () -> Unit,
    onForgotPassword: () -> Unit
) {
    val context = LocalContext.current
    val authViewModel = remember { AuthViewModel(navController, context) }
//    val biometricAuthHelper = remember { BiometricAuthHelper(context as androidx.fragment.app.FragmentActivity) }

    var emailOrPhone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Welcome Text
                Text(
                    text = "Welcome Back!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6200EE),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Company Name
                Text(
                    text = "LinkedLine Connect",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                // Email or Phone Input
                TextField(
                    value = emailOrPhone,
                    onValueChange = { emailOrPhone = it },
                    label = { Text("Email or Phone Number") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedIndicatorColor = Color(0xFF6200EE),
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color(0xFF6200EE)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password Input
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = if (passwordVisible) "Hide Password" else "Show Password"
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF5F5F5),
                        unfocusedContainerColor = Color(0xFFF5F5F5),
                        focusedIndicatorColor = Color(0xFF6200EE),
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color(0xFF6200EE)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Login with Fingerprint or FaceID
//                Button(
//                    onClick = {
//                        biometricAuthHelper.biometricLogin(
//                            onSuccess = {
//                                navController.navigate("dashboard")
//                            },
//                            onFailure = {
//                                // Handle failure (e.g., show a toast or error message)
//                            }
//                        )
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
//                ) {
//                    Text("Login with Fingerprint or FaceID", fontSize = 16.sp, color = Color.White)
//                }

                Spacer(modifier = Modifier.height(24.dp))

                // Login Button
                Button(
                    onClick = {
                        authViewModel.login(
                            email = emailOrPhone,
                            password = password
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text("Login", fontSize = 16.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sign Up Text
                Text(
                    text = "New here? Sign up",
                    fontSize = 16.sp,
                    color = Color(0xFF6200EE),
                    modifier = Modifier.clickable {
                        navController.navigate("welcome") },
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Forgot Password
                Text(
                    text = "Forgot Password?",
                    fontSize = 16.sp,
                    color = Color.Red,
                    modifier = Modifier.clickable { onForgotPassword() },
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}

// @Preview
// @Composable
// fun LoginScreenPreview() {
//     LoginScreen(
//         navController = NavHostController(LocalContext.current),
//         onNavigateToSignUp = {},
//         onForgotPassword = {}
//     )
// }

