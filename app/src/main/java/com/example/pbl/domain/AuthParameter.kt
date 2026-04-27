package com.example.pbl.domain

// params for register
data class RegisterParams(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val alias: String
)

// params for login
data class LoginParams(
    val email: String,
    val password: String
)