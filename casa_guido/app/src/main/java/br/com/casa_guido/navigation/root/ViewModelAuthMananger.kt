package br.com.casa_guido.navigation.root

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casa_guido.configuration.Sessao
import br.com.casa_guido.dto.RefreshTokenResponse
import br.com.casa_guido.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelAuthMananger(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    fun refreshToken(context: Context) {
        viewModelScope.launch {
            try {
                val response: RefreshTokenResponse = authRepository.refreshToken(context)
                Sessao.setaStatusSessao(context, true)
                _isAuthenticated.value = true
            } catch (e: Exception) {
                Sessao.setaStatusSessao(context, false)
                logout(context)
                _isAuthenticated.value = false
            }
        }
    }

    fun login(context: Context, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authRepository.loginUsuario(context, email, password)
                Sessao.setaStatusSessao(context, true)
                checkSession(context)
            } catch (e: Exception) {
                Sessao.setaStatusSessao(context, false)
            }
        }
    }

    fun logout(context: Context) {
        Sessao.clearSession(context)
        _isAuthenticated.value = false
    }

    private fun checkSession(context: Context) {
        _isAuthenticated.value = Sessao.isAuthenticated(context)
    }

}