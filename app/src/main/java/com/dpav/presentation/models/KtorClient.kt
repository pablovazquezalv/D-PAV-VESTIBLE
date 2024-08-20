package com.dpav.presentation.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private var userPreferences = UserPreferences(ContextProvider.context)
    private var client: HttpClient? = null

    init {
        initializeClient()
    }

    private fun getToken(): String? {
        return userPreferences.token
    }

    fun setInstance(){
        //println("Refresh Token set: ${userPreferences.token}")
        userPreferences = UserPreferences(ContextProvider.context)
        initializeClient()
    }

    private fun initializeClient() {
        client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(userPreferences.token ?: "", userPreferences.token ?: "")
                    }
                }
            }
            install(DefaultRequest) {
                header(HttpHeaders.Authorization, "Bearer ${userPreferences.token}")
            }
        }
    }
    fun getClient(): HttpClient {
        return client ?: throw IllegalStateException("Client not initialized")
    }
}

suspend fun validarToken(): HttpResponse {
    return KtorClient.getClient().request("https://api.dpav.shop/api/verificarToken") {
        method = HttpMethod.Post
    }
}

suspend fun getLogin(requestBody: Any): HttpResponse {
    return KtorClient.getClient().request("https://api.dpav.shop/api/loginVerificarCodigoSmartWatch") {
        method = HttpMethod.Post
        contentType(ContentType.Application.Json)
        setBody(requestBody)
    }
}

suspend fun getPerros(): HttpResponse {
    return KtorClient.getClient().request("https://api.dpav.shop/api/mostrarMascotaPorUsuario"){
        method = HttpMethod.Get
        //contentType(ContentType.Application.Json)
    }
}