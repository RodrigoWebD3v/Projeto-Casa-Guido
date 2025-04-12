package br.com.casa_guido.navigation.root

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casa_guido.configuration.Resultado
import br.com.casa_guido.configuration.Sessao
import br.com.casa_guido.dto.RefreshTokenResponse
import br.com.casa_guido.repository.AuthRepository
import br.com.casa_guido.service.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModelAuthMananger(
    private val authRepository: AuthRepository,
    private val authService: AuthService,
) : ViewModel() {

    private val _status = MutableStateFlow<Resultado>(Resultado.Carregando)
    val status = _status.asStateFlow()

    fun refreshToken(context: Context) {
        viewModelScope.launch {
            try {
                val response: RefreshTokenResponse = authRepository.refreshToken(context)
                _status.value = Resultado.Sucesso("")
            } catch (e: Exception) {
                Sessao.setaStatusSessao(context, false)
                _status.value = Resultado.Erro("")
            }
        }
    }

    fun login(context: Context, email: String, password: String) {
        viewModelScope.launch {
            try {
                authService.login(context, email, password)
            } catch (e: Exception) {
                Sessao.setaStatusSessao(context, false)
            }
        }
    }

}