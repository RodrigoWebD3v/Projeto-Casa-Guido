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

    var localHost: Boolean = true

    var endpointPrincipal = if (localHost) "http://10.0.2.2:3001" else "http://192.168.101.14:3001"

    val authEndpoint = "${endpointPrincipal}/api/v1/auth"

    val pacienteEndpoint = "http://10.0.2.2:3001/api/v1/paciente" //"https://webhook.site/6baf3350-0ad7-45c2-952b-e136b6bef9b5" 

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