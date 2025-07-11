package br.com.casa_guido.screens.home.uistate

import br.com.casa_guido.models.Agendamento
import br.com.casa_guido.screens.home.componentes.cads_state.CardsUiState


data class HomeUiState(
    val totalPacientes: CardsUiState.TotalPacientesCardUiState = CardsUiState.TotalPacientesCardUiState(),
    val pacientesHoje: CardsUiState.PacientesHojeCardUiState = CardsUiState.PacientesHojeCardUiState(),
    val pacientesSemana: CardsUiState.PacientesSemanaCardUiState = CardsUiState.PacientesSemanaCardUiState(),
    val comparecimento: CardsUiState.ComparecimentoCardUiState = CardsUiState.ComparecimentoCardUiState(),
    val agendamentos: List<Agendamento> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null,
)