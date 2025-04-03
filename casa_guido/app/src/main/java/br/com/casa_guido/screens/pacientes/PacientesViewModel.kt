package br.com.casa_guido.screens.pacientes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casa_guido.screens.listaPacientes
import br.com.casa_guido.screens.pacientes.uistate.PacientesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PacientesViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow<PacientesUiState>(PacientesUiState())
    val uiState = _uiState.asStateFlow()

    private fun carregarPacientes() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    pacientes = listaPacientes
                )
            }
        }
    }

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    buscaPaciente = { nome ->
                        _uiState.update { state ->
                            state.copy(nome = nome)
                        }
                    },
                )
            }
        }

        carregarPacientes()
    }
}