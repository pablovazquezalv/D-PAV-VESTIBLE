package com.dpav.presentation.models

import com.google.gson.Gson
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.HttpStatusCode

data class Perro(
    val id: Int,
    val nombre: String,
    val distintivo: String,
    val sexo: String,
    val peso: Double,
    val tamano: String,
    val estatus: Int,
    val esterilizado: String,
    val fecha_nacimiento: String,
    val imagen: String,
    val chip: String,
    val tipo: String,
    val id_raza: Int,
    val padre_id: Int,
    val madre_id: Int,
    val user_id: Int,
    val created_at: String,
    val updated_at: String,
)

data class PerroResponse(
    val message: String,
    val mascota: List<Perro>,
)

suspend fun fetchPerros(): List<Perro>? {
    val response = getPerros()
    if (response.status == HttpStatusCode.OK){
        val perroResponse = Gson().fromJson(response.bodyAsText(), PerroResponse::class.java)
        return perroResponse.mascota
    }
    else {
        println("Response: ${response.bodyAsText()}")
        println("Response: ${response.status}")
        return null
    }
}
