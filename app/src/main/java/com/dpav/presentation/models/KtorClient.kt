package com.dpav.presentation.models

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object KtorClient {
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
    }
}

suspend fun getLogin(url: String, requestBody: Any): HttpResponse {
    return KtorClient.client.post("http://134.209.35.1/api/loginVerificarCodigoSmartWatch") {
        contentType(ContentType.Application.Json)
        setBody(requestBody)
    }
}