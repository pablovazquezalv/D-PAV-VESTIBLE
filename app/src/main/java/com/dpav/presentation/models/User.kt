package com.dpav.presentation.models

data class User(
    val id: Int,
    val nombre: String,
    val usuario: String,
    val apellido_paterno: String,
    val telefono: String,
    val codigo: Int,
    val codigo_expiration: String,
    val email: String,
    val direccion: String,
    val ciudad: String,
    val colonia: String,
    val codigo_postal: Int,
    val activo: Boolean,
    val estado: String,
    val role_id: Int,
    val verification_code_sent_at: String,
    val email_verified_at: String,
    val created_at: String,
    val updated_at: String
)
