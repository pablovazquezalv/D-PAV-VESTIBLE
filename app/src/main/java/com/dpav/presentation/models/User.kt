package com.dpav.presentation.models

data class User(
    val id: Int,
    val nombre: String,
    val usuario: String,
    val apellido_paterno: String,
    val telefono: String,
    val codigo: Int,
    val email: String,
    val direccion: String,
    val ciudad: String,
    val colonia: String,
    val codigo_postal: Int,
    val activo: Int,
    val estado: Int,
    val role_id: Int
)
