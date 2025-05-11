package com.moses.linkedlineconnect.model

data class Escorts(
    val firstname: String = "",
    val lastname: String = "",
    val email: String = "",
    val password: String = "",
    val uid: String = "",
    val role: String = "",
    val phoneNumber: String = "",
    val idNumber: String = "",
    val age: String = "",
    val imageUri: String? // Store the URI of the uploaded image as a string
)