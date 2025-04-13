package br.com.casa_guido.screens.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ViewModelSincronizacao : ViewModel() {

    private val _status = MutableStateFlow<SincronizandoDados>(SincronizandoDados.Sincronizando)
    val status = _status.asStateFlow()

    fun setStatusSincronizacao(estado: SincronizandoDados) {
        _status.update {
            estado
        }
    }




}