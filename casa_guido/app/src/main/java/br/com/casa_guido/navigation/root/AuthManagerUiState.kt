package br.com.casa_guido.navigation.root

data class AuthManagerUiState(
    val isAuthenticated: Boolean = false,
    val errorMessage: String? = null,
)