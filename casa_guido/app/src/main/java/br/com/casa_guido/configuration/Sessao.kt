package br.com.casa_guido.configuration

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import br.com.casa_guido.configuration.exception.AppException

object Sessao {

    private const val PREF_NAME = "user_session"
    private const val KEY_IS_AUTHENTICATED = "is_authenticated"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setaStatusSessao(context: Context, isAuthenticated: Boolean) {
        getPreferences(context).edit() {
            putBoolean(KEY_IS_AUTHENTICATED, isAuthenticated)
        }
    }

    fun isAuthenticated(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_IS_AUTHENTICATED, false)
    }

    fun clearSession(context: Context) {
        getPreferences(context).edit() { clear() }
    }

    fun errosApp(e: Exception): AppException {
        return when (e) {
            is io.ktor.client.plugins.ServerResponseException -> {
                if (e.response.status.value == 401) {
                    AppException.SessaoExpirada()
                } else {
                    AppException.ErroDesconhecido()
                }
            }
            is io.ktor.client.plugins.ClientRequestException -> {
                if (e.response.status.value == 401) {
                    AppException.SessaoExpirada()
                } else {
                    AppException.ErroDesconhecido()
                }
            }
            is io.ktor.utils.io.errors.IOException -> AppException.SemConexao()
            else -> AppException.ErroDesconhecido()
        }
    }
}
