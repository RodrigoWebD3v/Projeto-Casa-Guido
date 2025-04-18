package br.com.casa_guido.screens.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _status = MutableStateFlow<SincronizandoDados>(SincronizandoDados.Sincronizando)
    val status = _status.asStateFlow()

    private val _primeiroAcesso = MutableStateFlow<Boolean>(false)
    val primeiroAcesso = _primeiroAcesso.asStateFlow()


    fun togglePrimeiroAcesso(){
        _primeiroAcesso.update {
            true
        }
    }
}