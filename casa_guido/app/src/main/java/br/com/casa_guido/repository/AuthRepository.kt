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


    suspend fun loginUsuario(email: String, senha: String): LoginResponse {

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

}