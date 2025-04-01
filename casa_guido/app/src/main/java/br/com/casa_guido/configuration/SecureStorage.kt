package br.com.casa_guido.configuration

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.core.content.edit

object SecureStorage {
    private const val PREFS_NAME = "secure_prefs"
    private const val TOKEN_KEY = "auth_token"
    private const val REFRESH_TOKEN_KEY = "refresh_token"

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
}
