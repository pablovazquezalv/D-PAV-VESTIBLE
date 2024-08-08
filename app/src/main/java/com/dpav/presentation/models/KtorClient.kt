package com.dpav.presentation.models

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import okhttp3.internal.format


object KtorClient {
    private var accessToken: String? = null
    private var refreshToken: String? = null
    fun setTokens() {
        val userPreferences = UserPreferences(ContextProvider.context)
        accessToken = userPreferences.getToken()
        refreshToken = userPreferences.getToken()
        println("Access Token set: $accessToken")
        println("Refresh Token set: $refreshToken")
    }

    fun clearTokens() {
        accessToken = null
        refreshToken = null
    }

    //Fin del bloque a checar
    val client = HttpClient(CIO){
        install(ContentNegotiation){
            json(Json{
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging){
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
        //Checar esta parte
        install(Auth) {
            bearer {
                loadTokens {
                    setTokens()
                    accessToken?.let { BearerTokens(it, refreshToken ?: "") }
                }
                /*refreshTokens {
                    refreshToken?.let {
                        val newTokens = performTokenRefresh()
                        setTokens()
                        BearerTokens(newTokens.accessToken, newTokens.refreshToken)
                    }
                }*/
            }
        }
        install(DefaultRequest) {
            header(HttpHeaders.Authorization, "Bearer $accessToken")
        }
    }
    private suspend fun performTokenRefresh(): BearerTokens {
        //Si jala cuando se le pone el token manual aqui
        //val newAccessToken = userPreferences.getToken()
        //val newRefreshToken = userPreferences.getToken()
        //println("Response ${newAccessToken}")
        return BearerTokens( "",  "")
        //2|MqxecdEKEc2J384O9loSDpVNkY2Yx0vNcSYR6YII19db75a3
    }
}

suspend fun validarToken(): HttpResponse {
    return KtorClient.client.request("http://134.209.35.1/api/verificarToken") {
        method = HttpMethod.Post
    }
}

suspend fun getLogin(requestBody: Any): HttpResponse {
    return KtorClient.client.request("http://134.209.35.1/api/loginVerificarCodigoSmartWatch") {
        method = HttpMethod.Post
        contentType(ContentType.Application.Json)
        setBody(requestBody)
    }
}

suspend fun getPerros(): HttpResponse {
    return KtorClient.client.request("http://134.209.35.1/api/mostrarMascotaPorUsuario"){
        method = HttpMethod.Get
        //contentType(ContentType.Application.Json)
    }
}