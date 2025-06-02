package br.com.casa_guido.screens.pacientes.uistate

import br.com.casa_guido.models.Paciente

data class PacientesUiState(
    val nome: String = "",
    val loading: Boolean = false,
    val error: String = "",
    val pacientes: List<Paciente> = emptyList<Paciente>(),
    val listaPacientesFiltrada: List<Paciente> = emptyList<Paciente>()
)