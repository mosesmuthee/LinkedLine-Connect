@file:Suppress("DEPRECATION")

package com.moses.linkedlineconnect.data

import android.app.ProgressDialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.moses.linkedlineconnect.navigation.ROUTE_ADMIN_DASHBOARD
import com.moses.linkedlineconnect.navigation.ROUTE_DASHBOARDEscort
import com.moses.linkedlineconnect.navigation.ROUTE_DASHBOARDParent
import com.moses.linkedlineconnect.navigation.ROUTE_LOGINParent

class AuthViewModel(
    private val navController: NavHostController,
    private val context: Context
) {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var isLoading: Boolean = false // Fixed property initialization

    private val progress: ProgressDialog = ProgressDialog(context).apply {
        setMessage("Loading...")
        setCancelable(false)
    }

    // Signup function
    fun signup(
        firstname: String,
        lastname: String,
        email: String,
        idNumber: String?,
        phoneNumber: String?,
        age: String?,
        password: String,
        confirmPassword: String,
        role: String,
    ) {
        Handler(Looper.getMainLooper()).post {}

        // Password check first (applies to all)
        if (password != confirmPassword) {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
            }
            return
        }

        // Role-specific validation
        when (role.lowercase()) {
            "parent" -> {
                if (firstname.isBlank() ||
                    lastname.isBlank() ||
                    phoneNumber.isNullOrBlank() ||
                    email.isBlank() ||
                    password.isBlank() ||
                    confirmPassword.isBlank()) {
                    Toast.makeText(context, "Please fill all parent fields", Toast.LENGTH_LONG).show()
                    return
                }
            }

            "escort" -> {
                if (firstname.isBlank() ||
                    lastname.isBlank() ||
                    idNumber.isNullOrBlank() ||
                    email.isBlank() ||
                    phoneNumber.isNullOrBlank() ||
                    age?.isBlank() == true ||
                    password.isBlank() ||
                    confirmPassword.isBlank()
                ) {
                    Toast.makeText(context, "Please fill all escort fields", Toast.LENGTH_LONG).show()
                    return
                }
            }

            "admin" -> {
                if (email.isBlank() ||
                    password.isBlank()) {
                    Toast.makeText(context, "Please provide admin email", Toast.LENGTH_LONG).show()
                    return
                }
            }

            else -> {
                Toast.makeText(context, "Unknown role: $role", Toast.LENGTH_LONG).show()
                return
            }
        }

        // If valid, proceed to create user
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            Handler(Looper.getMainLooper()).post {}

            if (task.isSuccessful) {
                val userId = mAuth.currentUser!!.uid
                val userData = mutableMapOf<String, Any>(
                    "firstname" to firstname,
                    "lastname" to lastname,
                    "email" to email,
                    "role" to role
                )

                // Add optional fields based on role
                if (!idNumber.isNullOrBlank()) userData["idNumber"] = idNumber
                if (!phoneNumber.isNullOrBlank()) userData["phoneNumber"] = phoneNumber

                val regRef = FirebaseDatabase.getInstance().getReference("Users/$userId")
                regRef.setValue(userData).addOnCompleteListener { dbTask ->
                    Handler(Looper.getMainLooper()).post {
                        if (dbTask.isSuccessful) {
                            Toast.makeText(context, "Registered Successfully", Toast.LENGTH_LONG).show()
                            navigateToDashboard(role)
                        } else {
                            Toast.makeText(context, "Error: ${dbTask.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            } else {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // Login function
    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Email and password cannot be blank", Toast.LENGTH_LONG).show()
            return
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = mAuth.currentUser!!.uid

                val userRef = FirebaseDatabase.getInstance().getReference("Users/$userId")
                userRef.get().addOnSuccessListener { dataSnapshot ->
                    if (dataSnapshot.exists()) {
                        val role = dataSnapshot.child("role").value?.toString()?.lowercase()

                        when (role) {
                            "parent" -> {
                                Toast.makeText(context, "Welcome, Parent 👨‍👩‍👧", Toast.LENGTH_SHORT).show()
                                navController.navigate(ROUTE_DASHBOARDParent)
                            }

                            "escort" -> {
                                Toast.makeText(context, "Welcome, Escort 🚗", Toast.LENGTH_SHORT).show()
                                navController.navigate(ROUTE_DASHBOARDEscort)
                            }

                            "admin" -> {
                                Toast.makeText(context, "Welcome, Admin 🛠️", Toast.LENGTH_SHORT).show()
                                navController.navigate(ROUTE_ADMIN_DASHBOARD)
                            }

                            else -> {
                                Toast.makeText(context, "Unknown role: $role", Toast.LENGTH_LONG).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "User data not found in database", Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(context, "Database error: ${exception.message}", Toast.LENGTH_LONG).show()
                }

            } else {
                Toast.makeText(context, "Login failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Logout function
    fun logout() {
        mAuth.signOut()
        navController.navigate(ROUTE_LOGINParent)
    }

    // Check if user is logged in
    fun isLoggedIn(): Boolean {
        return mAuth.currentUser != null
    }

    // Navigate to the appropriate dashboard based on the role
    private fun navigateToDashboard(role: String) {
        when (role.lowercase()) {
            "parent" -> navController.navigate(ROUTE_DASHBOARDParent)
            "escort" -> navController.navigate(ROUTE_DASHBOARDEscort)
            "admin" -> navController.navigate(ROUTE_ADMIN_DASHBOARD)
            else -> {
                Toast.makeText(context, "Unknown role: $role", Toast.LENGTH_LONG).show()
            }
        }
    }
}