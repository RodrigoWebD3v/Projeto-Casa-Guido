package br.com.casa_guido.screens.cadastro.components.CamposPaciente

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CamposPacienteViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow<CamposPacienteUiState>(CamposPacienteUiState())
    val uiState = _uiState.asStateFlow()

    fun onNomePacienteChange(nome: String) {
        _uiState.value = _uiState.value.copy(nomePaciente = nome)
    }

    fun onDataNascimentoChange(data: String) {
        _uiState.value = _uiState.value.copy(dataNascimento = data)
    }

    fun onNomeMaeChange(nome: String) {
        _uiState.value = _uiState.value.copy(nomeMae = nome)
    }

    fun onNomePaiChange(nome: String) {
        _uiState.value = _uiState.value.copy(nomePai = nome)
    }

    fun onNomeResponsavelChange(nome: String) {
        _uiState.value = _uiState.value.copy(nomeResponsavel = nome)
    }

    fun onCpfChange(cpf: String) {
        _uiState.value = _uiState.value.copy(cpf = cpf)
    }

    fun onRgChange(rg: String) {
        _uiState.value = _uiState.value.copy(rg = rg)
    }

    fun onCartaoSusChange(cartao: String) {
        _uiState.value = _uiState.value.copy(cartaoSus = cartao)
    }

    fun onCelularChange(celular: String) {
        _uiState.value = _uiState.value.copy(celular = celular)
    }

    fun onDataPickerShowChange(show: Boolean) {
        _uiState.value = _uiState.value.copy(dataPickerShow = show)
    }

    fun onLimparCampos() {
        _uiState.value = CamposPacienteUiState()
    }


}