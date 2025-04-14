package br.com.casa_guido.navigation.root

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casa_guido.configuration.Conexao
import br.com.casa_guido.configuration.Resultado
import br.com.casa_guido.configuration.SecureStorage
import br.com.casa_guido.configuration.SecureStorage.OfflinePermitido
import br.com.casa_guido.configuration.SecureStorage.clearTempoOffline
import br.com.casa_guido.configuration.SecureStorage.setaTempoOfflinePermitido
import br.com.casa_guido.configuration.Sessao
import br.com.casa_guido.configuration.Sessao.errosApp
import br.com.casa_guido.repository.AuthRepository
import br.com.casa_guido.service.AuthService
import br.com.casa_guido.service.PacienteService
import br.com.casa_guido.util.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModelAuthMananger(
    private val authService: AuthService,
) : ViewModel() {

    private val _status = MutableStateFlow<Resultado>(Resultado.SemInteracao)
    val status = _status.asStateFlow()

    private val _conexao = MutableStateFlow<Conexao>(Conexao.SemInformacao)
    val conexao = _conexao.asStateFlow()

    private val _usuarioLogado = MutableStateFlow<Boolean>(true)
    val statusUsuario = _usuarioLogado.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun refreshToken(context: Context) {
        viewModelScope.launch {
            try {
                if (!Utils.verificarConexao(context)) {
                    _conexao.update { Conexao.SemConexao }
                    if (OfflinePermitido(context)) {
                        _status.update {
                            Resultado.Sucesso("Conexão offline permitida")
                        }
                        Log.i("AuthMananger", "Entrou  Offline permitido")
                        return@launch
                    } else {
                        _status.update {
                            Resultado.Erro("Erro ao fazer login")
                        }
                        Log.i("AuthMananger", "Entrou  Offline não permitido")
                        return@launch
                    }
                }
                authService.refreshToken(context)
                _status.update {
                    Resultado.Sucesso("Sessão valida")
                }
                setaTempoOfflinePermitido(context)
                Log.i("AuthMananger", "Login realizado com sucesso via refresh token")
            } catch (e: Exception) {
                Log.i("AuthMananger", "Erro ao tentar buscar refreshtoken")
                if (!OfflinePermitido(context)) {
                    Log.i(
                        "AuthMananger",
                        "Redirecionando para tela de login porque offline não permitido"
                    )
                    Sessao.setaStatusSessao(context, false)
                    _status.update {
                        Resultado.Erro(e.message ?: "Erro ao fazer login")
                    }
                    clearTempoOffline(context)
                    return@launch
                }
                Log.i(
                    "AuthMananger",
                    "Redirecionando para dashboard porque offline permitido"
                )
                _status.update {
                    Resultado.Sucesso("Login realizado com sucesso")
                }
                Log.i("AuthMananger", "Entrou  Offline permitido")
                return@launch
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun login(context: Context, email: String, password: String) {
        viewModelScope.launch {
            try {
//                if (!Utils.verificarConexao(context)) {
//                    Log.i("AuthMananger", "Login Sem conexão")
//                    _conexao.update { Conexao.SemConexao }
//                    setStatus(Resultado.Erro("Sem conexão com a internet"))
//                    return@launch
//                } else {
//                    _conexao.update { Conexao.Conectado }
//                }
                authService.login(context, email, password)
                _status.update {
                    Resultado.Sucesso("Login realizado com sucesso")
                }
                setaTempoOfflinePermitido(context)
            } catch (e: Exception) {
                val erroTratado = errosApp(e)
                Log.i("AuthMananger", erroTratado.toString())
                Sessao.setaStatusSessao(context, false)
                _status.update {
                    Resultado.Erro(e.message ?: "Erro ao fazer login")
                }
            }
        }
    }

    fun logout(context: Context) {
        viewModelScope.launch {
            try {
                authService.logout(context)
                clearTempoOffline(context)
                Sessao.setaStatusSessao(context, false)
                SecureStorage.clear(context)
                _usuarioLogado.update {
                    false
                }
            } catch (e: Exception) {
                Log.i("AuthMananger", "Erro ao fazer logout")
                _status.update {
                    Resultado.Erro(e.message ?: "Erro ao fazer logout")
                }
            }
        }
    }

    fun setStatus(resultado: Resultado) {
        _status.update {
            resultado
        }
    }


}