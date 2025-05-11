package com.moses.linkedlineconnect.data


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseAuthManager {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun loginUserWithRole(
        email: String,
        password: String,
        callback: (Boolean, FirebaseUser?, String?, String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val userId = auth.currentUser?.uid
                if (userId != null) {
                    firestore.collection("users").document(userId).get()
                        .addOnSuccessListener { document ->
                            val role = document.getString("role")
                            if (role != null) {
                                callback(true, auth.currentUser, role, null)
                            } else {
                                callback(false, null, null, "Role not found")
                            }
                        }
                        .addOnFailureListener {
                            callback(false, null, null, "Failed to fetch user role")
                        }
                } else {
                    callback(false, null, null, "User ID is null")
                }
            }
            .addOnFailureListener {
                callback(false, null, null, it.message)
            }
    }

    fun logout() {
        auth.signOut()
    }
}
