package br.com.casa_guido.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casa_guido.dto.LoginResponse
import br.com.casa_guido.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    private val _data = MutableStateFlow<LoginResponse?>(null)
    val data = _data.asStateFlow()

    private fun login() {
        try {
            viewModelScope.launch {
                val resposta = repository.loginUsuario("joao11@example.com", "senha123")
                _data.value = resposta
            }
        } catch (e: Exception) {
            println("Erro ao buscar dados: ${e.message}")
        }
    }

    init {
        login()
    }
}