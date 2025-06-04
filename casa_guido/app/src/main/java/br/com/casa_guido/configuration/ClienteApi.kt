package br.com.casa_guido.configuration

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ClienteApi {

    var localHost: Boolean = false

    var endpointPrincipal = if (localHost) "http://10.0.2.2:3000" else "https://a05e-2804-4bd0-482-e200-70bd-90ac-9d36-bf51.ngrok-free.app" //"http://192.168.101.14:3000"

    val authEndpoint = "${endpointPrincipal}/api/v1/auth"

    val pacienteEndpoint = "https://webhook.site/198ad44b-1f0f-4d46-8c04-cf2cb8b46626"

    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json { ignoreUnknownKeys = true },
            )
        }
        install(Logging) {
            level = LogLevel.INFO
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15000  // Timeout total da requisição (15 segundos)
            connectTimeoutMillis = 10000  // Tempo para estabelecer conexão (10 segundos)
            //socketTimeoutMillis = 15000   // Tempo de inatividade da conexão (15 segundos)
        }


        defaultRequest {
            headers {
                append("Content-Type", ContentType.Application.Json.toString())
            }
        }

    }
}