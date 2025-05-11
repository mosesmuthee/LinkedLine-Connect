package com.moses.linkedlineconnect.ui.theme.dashboards

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser
import com.moses.linkedlineconnect.R
import com.moses.linkedlineconnect.data.FirebaseAuthManager


class LoginActivity : AppCompatActivity() {

    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var loginButton: Button
    private val authManager = FirebaseAuthManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailField = findViewById(R.id.editTextEmail)
        passwordField = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)

        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                authManager.loginUserWithRole(email, password) { success, user, role, error ->
                    if (success && user != null && role != null) {
                        handleRoleLogin(role, user)
                    } else {
                        Toast.makeText(this, "Login failed: $error", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleRoleLogin(role: String, user: FirebaseUser) {
        when (role.lowercase()) {
            "parent" -> {
                Toast.makeText(this, "Welcome Parent!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ParentActivity::class.java))
            }
            "escort" -> {
                Toast.makeText(this, "Welcome Escort!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, EscortActivity::class.java))
            }
            "admin" -> {
                Toast.makeText(this, "Welcome Admin!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AdminActivity::class.java))
            }
            else -> {
                Toast.makeText(this, "Unknown role: $role", Toast.LENGTH_SHORT).show()
            }
        }
        finish()
    }
}
