package br.com.casa_guido.screens.home.componentes.cads_state

class CardsUiState {
    data class TotalPacientesCardUiState(
        val title: String = "Total de Pacientes",
        val valorPrincipal: String = "248",
        val subtitulo: String = "+12% este mes",
    )

    data class PacientesHojeCardUiState(
        val title: String = "Hoje",
        val valorPrincipal: String = "12",
        val subtitulo: String = "3 pendentes",
    )

    data class PacientesSemanaCardUiState(
        val title: String = "Semana",
        val valorPrincipal: String = "42",
        val subtitulo: String = "+8% vs anterior",
    )

    data class ComparecimentoCardUiState(
        val title: String = "Comparecimento",
        val valorPrincipal: String = "92%",
        val subtitulo: String = "+2% este mes",
    )
}