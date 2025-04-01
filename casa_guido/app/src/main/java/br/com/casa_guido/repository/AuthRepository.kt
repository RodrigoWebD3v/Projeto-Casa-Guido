package br.com.casa_guido.repository

import android.content.Context
import br.com.casa_guido.configuration.ClienteApi
import br.com.casa_guido.configuration.SecureStorage
import br.com.casa_guido.dto.LoginRequest
import br.com.casa_guido.dto.LoginResponse
import br.com.casa_guido.dto.RefreshTokenRequest
import br.com.casa_guido.dto.RefreshTokenResponse
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(
    private val clienteApi: ClienteApi
) {


    suspend fun loginUsuario(context: Context, email: String, senha: String): LoginResponse {

        val loginRequest = LoginRequest(
            email = email,
            password = senha
        )

        return try {
            withContext(Dispatchers.IO) {
                val response =
                    clienteApi.client.post("${clienteApi.authEndpoint}/login") {
                        setBody(loginRequest)
                    }
                SecureStorage.saveToken(context, response.body<LoginResponse>().token)
                SecureStorage.saveRefreshToken(
                    context,
                    response.body<LoginResponse>().refreshToken
                )
                response.body<LoginResponse>()
            }
        } catch (e: Exception) {
            throw Exception("Erro ao buscar dados: ${e.message}")
        }
    }

    suspend fun refreshToken(context: Context): RefreshTokenResponse {

        val refreshToken: String = SecureStorage.getRefreshToken(context).toString()

        val refreshTokenReq = RefreshTokenRequest(
            refreshToken = refreshToken
        )

        return try {
            withContext(Dispatchers.IO) {
                val response =
                    clienteApi.client.post("${clienteApi.authEndpoint}/refresh") {
                        setBody(refreshTokenReq)
                    }
                SecureStorage.saveToken(context, response.body<RefreshTokenResponse>().token)
                response.body<RefreshTokenResponse>()
            }
        } catch (e: Exception) {
            throw Exception("Erro ao buscar dados: ${e.message} - ${e.cause}")
        }
    }

//    suspend fun fetchData(): String? {
//        return try {
//
//            withContext(Dispatchers.IO) {
//                val response =
//                    clienteApi.client.get("https://webhook.site/5d7b00dd-5604-4b11-b832-162bde9134df")
//                response.body<String>()
//            }
//        } catch (e: Exception) {
//            println("Erro ao buscar dados: ${e.message}")
//            null
//        }
//    }

}