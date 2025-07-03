package br.com.casa_guido.screens.main

import android.content.Context
import androidx.lifecycle.ViewModel
import br.com.casa_guido.service.SincronizarPacientesService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val sincronizarPacientesService: SincronizarPacientesService
) : ViewModel() {

    private val _status = MutableStateFlow<SincronizandoDados>(SincronizandoDados.Sincronizando)
    val status = _status.asStateFlow()

    private val _context = MutableStateFlow<Context?>(null)

    private val _primeiroAcesso = MutableStateFlow<Boolean>(false)
    val primeiroAcesso = _primeiroAcesso.asStateFlow()


    fun togglePrimeiroAcesso(){
        _primeiroAcesso.update {
            true
        }
    }

    fun setContext(context: Context) {
        _context.update { context }
    }

    suspend fun SincronizarPacientes() {
        coroutineScope {
            sincronizarPacientesService.SincronizarPacientes(_context.value!!)
        }

    }

}