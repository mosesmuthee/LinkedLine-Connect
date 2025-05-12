package com.moses.linkedlineconnect.model

data class Parents(
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
