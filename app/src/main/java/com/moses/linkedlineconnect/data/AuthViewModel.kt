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
        idNumber: String,
        phoneNumber: String,
        password: String,
        confirmPassword: String,
        role: String,
    ) {
        Handler(Looper.getMainLooper()).post {
//            progress.show()
        }

        if (firstname.isBlank() ||
            lastname.isBlank() ||
            idNumber.isBlank() ||
            phoneNumber.isBlank() ||
            email.isBlank() ||
            password.isBlank() ||
            confirmPassword.isBlank()
        ) {
            Handler(Looper.getMainLooper()).post {
//                progress.dismiss()
                Toast.makeText(context, "Credentials cannot be blank", Toast.LENGTH_LONG).show()
            }
            return
        }

        if (password != confirmPassword) {
            Handler(Looper.getMainLooper()).post {
//                progress.dismiss()
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
            }
            return
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            Handler(Looper.getMainLooper()).post {
//                progress.dismiss()
            }

            if (task.isSuccessful) {
                val userId = mAuth.currentUser!!.uid
                val userData = mapOf(
                    "email" to email,
                    "role" to role
                )

                val regRef = FirebaseDatabase.getInstance().getReference("Users/$userId")
                regRef.setValue(userData).addOnCompleteListener { dbTask ->
                    Handler(Looper.getMainLooper()).post {
                        if (dbTask.isSuccessful) {
                            Toast.makeText(context, "Registered Successfully", Toast.LENGTH_LONG)
                                .show()
                            navController.navigate(ROUTE_DASHBOARDParent)
                        } else {
                            Toast.makeText(
                                context,
                                "Error: ${dbTask.exception?.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            } else {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    // Login function
    fun login(email: String, password: String) {
//        progress.show()

        if (email.isBlank() || password.isBlank()) {
            Handler(Looper.getMainLooper()).post {
//                progress.dismiss()
                Toast.makeText(context, "Email and password cannot be blank", Toast.LENGTH_LONG)
                    .show()
            }
            return
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            Handler(Looper.getMainLooper()).post {
//                progress.dismiss()
            }

            if (task.isSuccessful) {
                val userId = mAuth.currentUser!!.uid

                val userRef = FirebaseDatabase.getInstance().getReference("Users/$userId")
                userRef.get().addOnSuccessListener { dataSnapshot ->
                    if (dataSnapshot.exists()) {
                        val role = dataSnapshot.child("role").value.toString()
                        navigateToDashboard(role)
                    } else {
                        Toast.makeText(context, "User data not found", Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(context, "Error: ${exception.message}", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_LONG)
                    .show()
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