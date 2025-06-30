package br.com.casa_guido.navigation.root

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casa_guido.configuration.Conexao
import br.com.casa_guido.configuration.Status
import br.com.casa_guido.configuration.SecureStorage
import br.com.casa_guido.configuration.SecureStorage.OfflinePermitido
import br.com.casa_guido.configuration.SecureStorage.clearTempoOffline
import br.com.casa_guido.configuration.SecureStorage.setaTempoOfflinePermitido
import br.com.casa_guido.configuration.Sessao
import br.com.casa_guido.configuration.Sessao.errosApp
import br.com.casa_guido.service.AuthService
import br.com.casa_guido.util.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModelAuthMananger(
    private val authService: AuthService,
) : ViewModel() {

    private val _status = MutableStateFlow<Status>(Status.SemInteracao)
    val status = _status.asStateFlow()

    private val _conexao = MutableStateFlow<Conexao>(Conexao.SemInformacao)
    val conexao = _conexao.asStateFlow()

    private val _usuarioLogado = MutableStateFlow<Boolean>(true)
    val statusUsuario = _usuarioLogado.asStateFlow()

    private val _email = MutableStateFlow<String>("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow<String>("")
    val password = _password.asStateFlow()

    fun setEmail(email: String) {
        _email.update { email }
    }

    fun setPassword(password: String)
    {
        _password.update { password }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun refreshToken(context: Context) {
        viewModelScope.launch {
            try {
                if (!Utils.verificarConexao(context)) {
                    _conexao.update { Conexao.SemConexao }
                    if (OfflinePermitido(context)) {
                        _status.update {
                            Status.Sucesso("Conexão offline permitida")
                        }
                        return@launch
                    } else {
                        _status.update {
                            Status.Erro("Erro ao fazer login")
                        }
                        return@launch
                    }
                }
                authService.refreshToken(context)
                _status.update {
                    Status.Sucesso("Sessão valida")
                }
                setaTempoOfflinePermitido(context)
            } catch (e: Exception) {
                if (!OfflinePermitido(context)) {
                    Sessao.setaStatusSessao(context, false)
                    _status.update {
                        Status.Erro(e.message ?: "Erro ao fazer login")
                    }
                    clearTempoOffline(context)
                    return@launch
                }
                _status.update {
                    Status.Sucesso("Login realizado com sucesso")
                }
                return@launch
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun login(context: Context, email: String, password: String) {
        viewModelScope.launch {
            try {
                if (!Utils.verificarConexao(context)) {
                    _conexao.update { Conexao.SemConexao }
                    setStatus(Status.Erro("Sem conexão com a internet"))
                    return@launch
                } else {
                    _conexao.update { Conexao.Conectado }
                }
                authService.login(context, email, password)
                _status.update {
                    Status.Sucesso("Login realizado com sucesso")
                }
                setaTempoOfflinePermitido(context)
            } catch (e: Exception) {
                val erroTratado = errosApp(e)
                Sessao.setaStatusSessao(context, false)
                _status.update {
                    Status.Erro(e.message ?: "Erro ao fazer login")
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
                _status.update {
                    Status.Erro(e.message ?: "Erro ao fazer logout")
                }
            }
        }
    }

    fun setStatus(resultado: Status) {
        _status.update {
            resultado
        }
    }

    suspend fun login()
    {

    }




}