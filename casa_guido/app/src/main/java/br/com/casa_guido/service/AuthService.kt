package br.com.casa_guido.service

import android.content.Context
import android.util.Log
import br.com.casa_guido.configuration.SecureStorage
import br.com.casa_guido.configuration.Sessao
import br.com.casa_guido.configuration.exception.AppException
import br.com.casa_guido.dto.LoginResponse
import br.com.casa_guido.repository.AuthRepository
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ServerResponseException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class AuthService(
    private val authRepository: AuthRepository,
) {

    suspend fun login(context: Context, email: String, password: String): LoginResponse {
        try {
            Sessao.setaStatusSessao(context, true)
            val response = authRepository.loginUsuario(email, password)

            saveToken(context, response.token, response.refreshToken)
            return response
        } catch (e: Exception) {
            Sessao.setaStatusSessao(context, false)
            throw Exception(e)
        }
    }

    suspend fun refreshToken(context: Context) {
        try {
            val refreshToken: String = SecureStorage.getRefreshToken(context).toString()
            val response = authRepository.refreshToken(context, refreshToken)
            saveToken(context, response.token, refreshToken)
            Sessao.setaStatusSessao(context, true)
        } catch (e: Exception) {
            Sessao.setaStatusSessao(context, false)
            throw Exception(e)
        }
    }


    fun saveToken(context: Context, token: String, refreshToken: String) {
        SecureStorage.saveToken(context, token)
        SecureStorage.saveRefreshToken(
            context,
            refreshToken
        )
    }

    fun logout(context: Context) {

    }

}