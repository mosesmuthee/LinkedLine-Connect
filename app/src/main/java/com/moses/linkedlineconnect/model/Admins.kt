package com.moses.linkedlineconnect.model

data class Admins(
    val email: String,
    val password: String,
    val profileImage: String? = null
)