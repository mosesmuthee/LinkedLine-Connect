package com.moses.linkedlineconnect.data

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class AuthHandler {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    suspend fun registerUser(
        role: String,
        formData: Map<String, String>,
        imageUri: Uri?,
        onSuccess: (FirebaseUser) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val email = formData["email"] ?: return onFailure("Email is required.")
        val password = formData["password"] ?: return onFailure("Password is required.")

        try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user ?: return onFailure("User creation failed.")

            // Upload profile image if available
            var imageUrl = ""
            if (imageUri != null) {
                val imageRef = storage.reference.child("profiles/${user.uid}.jpg")
                val uploadTask = imageRef.putFile(imageUri).await()
                imageUrl = imageRef.downloadUrl.await().toString()
            }

            // Build user profile data
            val userData = mutableMapOf(
                "uid" to user.uid,
                "email" to email,
                "role" to role,
                "profileImage" to imageUrl
            )
            userData.putAll(formData)

            // Save to Firestore in role-specific collection
            val collectionName = when (role) {
                "Parent" -> "parents"
                "Escort" -> "escorts"
                "Admin" -> "admins"
                else -> "users"
            }

            firestore.collection(collectionName).document(user.uid).set(userData).await()

            onSuccess(user)
        } catch (e: Exception) {
            onFailure(e.message ?: "Registration failed.")
        }
    }
}
