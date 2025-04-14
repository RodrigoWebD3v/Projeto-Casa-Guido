package br.com.casa_guido.screens.pacientes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casa_guido.screens.pacientes.uistate.PacientesUiState
import br.com.casa_guido.util.ListaMemoria
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PacientesViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow<PacientesUiState>(PacientesUiState())
    val uiState = _uiState.asStateFlow()

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    private fun carregarPacientes() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    pacientes = ListaMemoria.pacientesLista,
                    listaPacientesFiltrada = ListaMemoria.pacientesLista,
                )
            }
            _loading.value = false
        }
    }


    fun filtrarPacientes(nome: String) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    nome = nome,
                    listaPacientesFiltrada = state.pacientes.filter { paciente ->
                        paciente.pessoa.nome.contains(nome, ignoreCase = true)
                    }
                )
            }
        }
    }

    init {
        carregarPacientes()
    }
}