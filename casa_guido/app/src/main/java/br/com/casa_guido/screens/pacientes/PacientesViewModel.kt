package br.com.casa_guido.screens.pacientes

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.casa_guido.screens.pacientes.uistate.PacientesUiState
import br.com.casa_guido.service.CompartilharArquivoService
import br.com.casa_guido.service.CriarPdfService
import br.com.casa_guido.service.PacienteService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PacientesViewModel(
    private val pacienteService: PacienteService,
    private val gerarPDFService: CriarPdfService,
    private val compartilharArquivoService: CompartilharArquivoService
) : ViewModel() {

    private val _uiState = MutableStateFlow<PacientesUiState>(PacientesUiState())
    val uiState = _uiState.asStateFlow()

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    fun carregarPacientes() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    pacientes = pacienteService.getPacientes(),
                    listaPacientesFiltrada = pacienteService.getPacientes(),
                )
            }
            _loading.value = false
        }
    }

    fun filtrarPacientes(nome: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    nome = nome,
                    listaPacientesFiltrada = it.pacientes.filter { paciente ->
                        paciente.pessoa.nome.contains(nome, ignoreCase = true)
                    }
                )
            }
        }
    }

    fun gerarPdf(idEdicao: String, context: Context) {
        val pdfArquivo = gerarPDFService.gerarFichaIdentificacaoPdf(
            context = context,
            paciente = _uiState.value.listaPacientesFiltrada.find { it.id == idEdicao }!!,
        )
        compartilharArquivoService.compartilharPdf(context, pdfArquivo)
    }

}