package br.com.casa_guido.configuration

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.time.LocalDate
import java.time.temporal.ChronoUnit

object SecureStorage {
    private const val PREFS_NAME = "secure_prefs"
    private const val TOKEN_KEY = "auth_token"
    private const val REFRESH_TOKEN_KEY = "refresh_token"
    private const val TIME_TO_DISCONECT = "time_to_disconect"

    private fun getSharedPreferences(context: Context) =
        EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun saveToken(context: Context, token: String) {
        getSharedPreferences(context).edit() { putString(TOKEN_KEY, token) }
    }

    fun getToken(context: Context): String? {
        return getSharedPreferences(context).getString(TOKEN_KEY, null)
    }

    fun saveRefreshToken(context: Context, refreshToken: String) {
        getSharedPreferences(context).edit() { putString(REFRESH_TOKEN_KEY, refreshToken) }
    }

    fun getRefreshToken(context: Context): String? {
        return getSharedPreferences(context).getString(REFRESH_TOKEN_KEY, null)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setaTempoOfflinePermitido(context: Context) {
        getSharedPreferences(context).edit() {
            putString(
                TIME_TO_DISCONECT,
                LocalDate.now().toString()
            )
        }
    }

    fun getTempoOfflinePermitido(context: Context): String? {
        return getSharedPreferences(context).getString(TIME_TO_DISCONECT, null)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun OfflinePermitido(context: Context): Boolean {
        val dataRegistro = getSharedPreferences(context).getString(TIME_TO_DISCONECT, null)
        if (dataRegistro != null) {
            val dataAtual = LocalDate.now()
            val dataRegistroLocal = LocalDate.parse(dataRegistro)
            val diasDeDiferenca = ChronoUnit.DAYS.between(dataRegistroLocal, dataAtual)
            return diasDeDiferenca < 3
        } else {
            return false
        }
    }

    fun clearTempoOffline(context: Context) {
        getSharedPreferences(context).edit() {
            remove(TIME_TO_DISCONECT)
        }
    }

    fun clear(context: Context) {
        getSharedPreferences(context).edit() {
            remove(TOKEN_KEY)
            remove(REFRESH_TOKEN_KEY)
            remove(TIME_TO_DISCONECT)
        }
    }



}
