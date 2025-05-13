package br.com.casa_guido.screens.login

data class LoginUiState(
    val email: String = "TESTESTESTE",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoginEnabled: Boolean = false
)