package br.com.casa_guido.configuration

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiViaCepConfig {

    val _endpointViaCep = "https://viacep.com.br/ws/"

    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json { ignoreUnknownKeys = true },
            )
        }
        install(Logging) {
            level = LogLevel.INFO
        }

        defaultRequest {
            headers {
                append("Content-Type", ContentType.Application.Json.toString())
            }
        }

    }

}