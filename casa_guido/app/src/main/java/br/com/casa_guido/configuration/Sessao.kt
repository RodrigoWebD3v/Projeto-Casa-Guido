package br.com.casa_guido.configuration

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

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
}
