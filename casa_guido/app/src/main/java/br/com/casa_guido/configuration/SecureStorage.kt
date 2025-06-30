package br.com.casa_guido.configuration

import android.content.Context
import android.os.Build
import android.util.Log
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


    private fun getSafeEncryptedSharedPreferences(context: Context) = try {
        EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    } catch (e: Exception) {
        // Remove os dados corrompidos
        context.deleteSharedPreferences(PREFS_NAME)
        // Tenta novamente com prefs limpos
        EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveToken(context: Context, token: String) {
        getSafeEncryptedSharedPreferences(context).edit { putString(TOKEN_KEY, token) }
    }

    fun getToken(context: Context): String? {
        return getSafeEncryptedSharedPreferences(context).getString(TOKEN_KEY, null)
    }

    fun saveRefreshToken(context: Context, refreshToken: String) {
        getSafeEncryptedSharedPreferences(context).edit { putString(REFRESH_TOKEN_KEY, refreshToken) }
    }

    fun getRefreshToken(context: Context): String? {
        return getSafeEncryptedSharedPreferences(context).getString(REFRESH_TOKEN_KEY, null)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setaTempoOfflinePermitido(context: Context) {
        getSafeEncryptedSharedPreferences(context).edit {
            putString(TIME_TO_DISCONECT, LocalDate.now().toString())
        }
    }

    fun getTempoOfflinePermitido(context: Context): String? {
        return getSafeEncryptedSharedPreferences(context).getString(TIME_TO_DISCONECT, null)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun OfflinePermitido(context: Context): Boolean {
        val dataRegistro = getSafeEncryptedSharedPreferences(context).getString(TIME_TO_DISCONECT, null)
        return if (dataRegistro != null) {
            val dataAtual = LocalDate.now()
            val dataRegistroLocal = LocalDate.parse(dataRegistro)
            val diasDeDiferenca = ChronoUnit.DAYS.between(dataRegistroLocal, dataAtual)
            diasDeDiferenca < 3
        } else {
            false
        }
    }

    fun clearTempoOffline(context: Context) {
        getSafeEncryptedSharedPreferences(context).edit {
            remove(TIME_TO_DISCONECT)
        }
    }

    fun clear(context: Context) {
        getSafeEncryptedSharedPreferences(context).edit {
            remove(TOKEN_KEY)
            remove(REFRESH_TOKEN_KEY)
            remove(TIME_TO_DISCONECT)
        }
    }
}
