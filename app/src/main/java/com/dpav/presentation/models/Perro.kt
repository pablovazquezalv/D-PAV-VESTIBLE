package com.dpav.presentation.models

import com.google.gson.Gson
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

data class Perro(
    val id: Int,
    val nombre: String,
    val raza: String
)

suspend fun fetchPerros(): List<Perro>? {
    val response = getPerros()
    if (response.status == HttpStatusCode.OK){
        println("Response: ${response.bodyAsText()}")
        return Gson().fromJson(response.bodyAsText(), Array<Perro>::class.java).toList()
    }
    else {
        return null
    }
}
