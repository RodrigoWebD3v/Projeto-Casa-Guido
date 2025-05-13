package br.com.casa_guido.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casa_guido.service.SincronizarPacientesService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val sincronizarPacientesService: SincronizarPacientesService
) : ViewModel() {

    private val _status = MutableStateFlow<SincronizandoDados>(SincronizandoDados.Sincronizando)
    val status = _status.asStateFlow()

    private val _primeiroAcesso = MutableStateFlow<Boolean>(false)
    val primeiroAcesso = _primeiroAcesso.asStateFlow()


    fun togglePrimeiroAcesso(){
        _primeiroAcesso.update {
            true
        }
    }

    init {
        viewModelScope.launch {
            sincronizarPacientesService.SincronizarPacientes()
        }
    }
}