package br.com.casa_guido.repository

import br.com.casa_guido.dto.LoginRequest
import br.com.casa_guido.dto.LoginResponse
import br.com.casa_guido.configuration.ClienteApi
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(
    private val clienteApi: ClienteApi
) {


    suspend fun loginUsuario(email: String, senha: String): LoginResponse? {

        val loginRequest = LoginRequest(
            email = email,
            password = senha
        )

        return try {
            withContext(Dispatchers.IO) {
                val response =
                    clienteApi.client.post("http://10.0.2.2:3000/api/v1/auth/login") {
                        setBody(loginRequest)
                    }
                response.body<LoginResponse>()
            }
        } catch (e: Exception) {
            println("Erro ao buscar dados: ${e.message}")
            null
        }
    }

    suspend fun fetchData(): String? {
        return try {

            withContext(Dispatchers.IO) {
                val response =
                    clienteApi.client.get("https://webhook.site/5d7b00dd-5604-4b11-b832-162bde9134df")
                response.body<String>()
            }
        } catch (e: Exception) {
            println("Erro ao buscar dados: ${e.message}")
            null
        }
    }

}