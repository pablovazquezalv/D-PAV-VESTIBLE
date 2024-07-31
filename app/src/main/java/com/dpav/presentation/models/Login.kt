package com.dpav.presentation.models

data class Login(
    val codigo: String
)

data class LoginResponse(
    val message: String,
    val user: User,
    val token: String
)
