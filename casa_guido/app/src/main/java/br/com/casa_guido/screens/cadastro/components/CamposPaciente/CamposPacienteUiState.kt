package br.com.casa_guido.screens.cadastro.components.CamposPaciente

data class CamposPacienteUiState(
    val nomePaciente: String = "",
    val dataNascimento: String = "",
    val nomeMae: String = "",
    val nomePai: String = "",
    val nomeResponsavel: String = "",
    val cpf: String = "",
    val rg: String = "",
    val cartaoSus: String = "",
    val celular: String = "",
    val dataPickerShow: Boolean = false,
)
