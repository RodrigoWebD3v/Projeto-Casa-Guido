package br.com.casa_guido.screens.components.escolaridade

import androidx.lifecycle.ViewModel
import br.com.casa_guido.configuration.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EscolaridadeViewModel : ViewModel() {
    private val _escolaridadeUiState = MutableStateFlow<EscolaridadeUiState>(EscolaridadeUiState())
    val escolaridadeUiState = _escolaridadeUiState.asStateFlow()

    fun updateEscolaridade(escolaridade: Pair<String, Int>, serie: Pair<String, Int>) {
        _escolaridadeUiState.value = _escolaridadeUiState.value.copy(
            escolaridade = escolaridade,
            serie = serie
        )
    }

    fun updateEscolaridadePorId(idEscolaridade: Int, idSerie: Int)
    {
        _escolaridadeUiState.value = _escolaridadeUiState.value.copy(
            escolaridade = _escolaridadeUiState.value.escolaridadeOptions.first { it.second == idEscolaridade },
            serie = _escolaridadeUiState.value.serieMedioOptions.first { it.second == idSerie }
        )
    }
}