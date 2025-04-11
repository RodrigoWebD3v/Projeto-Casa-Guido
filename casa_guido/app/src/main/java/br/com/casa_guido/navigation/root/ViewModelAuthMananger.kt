package br.com.casa_guido.navigation.root

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casa_guido.configuration.Sessao
import br.com.casa_guido.dto.RefreshTokenResponse
import br.com.casa_guido.repository.AuthRepository
import br.com.casa_guido.service.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelAuthMananger(
    private val authRepository: AuthRepository,
    private val authService: AuthService
) : ViewModel() {

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    fun refreshToken(context: Context) {
        viewModelScope.launch {
            try {
                val response: RefreshTokenResponse = authRepository.refreshToken(context)

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
                authService.login(context, email, password)
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
        println("CHECK SESSAO"+ Sessao.isAuthenticated(context))
        _isAuthenticated.value = Sessao.isAuthenticated(context)
    }

}