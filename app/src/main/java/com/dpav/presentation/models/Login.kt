package com.dpav.presentation.models

import com.google.gson.Gson
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

data class Login(
    val codigo: String
)

data class LoginResponse(
    val message: String,
    val user: User,
    val token: String
)


suspend fun valToken(): Boolean {
    val response = validarToken()
    return response.status == HttpStatusCode.OK
}