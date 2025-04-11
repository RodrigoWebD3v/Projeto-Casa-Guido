package br.com.casa_guido.service

import android.content.Context
import br.com.casa_guido.configuration.SecureStorage
import br.com.casa_guido.configuration.Sessao
import br.com.casa_guido.dto.LoginResponse
import br.com.casa_guido.repository.AuthRepository

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
            throw Exception("Erro ao buscar dados: ${e.message}")
        }
    }


    fun saveToken(context: Context, token: String, refreshToken: String) {
        SecureStorage.saveToken(context, token)
        SecureStorage.saveRefreshToken(
            context,
            refreshToken
        )
    }

}