package br.com.casa_guido.dto

import kotlinx.serialization.Serializable


@Serializable
data class LoginResponse(
    val token: String,
    val refreshToken: String,
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class RefreshTokenRequest(
    val refreshToken: String
)

@Serializable
data class RefreshTokenResponse(
    val token: String,
)