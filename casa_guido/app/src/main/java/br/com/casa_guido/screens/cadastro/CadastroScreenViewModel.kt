package br.com.casa_guido.screens.cadastro

import androidx.lifecycle.ViewModel
import br.com.casa_guido.screens.shared.DropDownMenuItem
import br.com.casa_guido.util.ListaMemoria
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CadastroScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<CadastroScreenUiState>(CadastroScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun setItemSelecionado(item: DropDownMenuItem) {
        _uiState.value = _uiState.value.copy(itemSelecionado = item)
    }

    fun getUsuario(id: String) {
        var paciente = ListaMemoria.pacientesLista.filter { item ->
            item.id == id
        }

        if (paciente.isNotEmpty()) {
            _uiState.value = _uiState.value.copy(
                paciente = paciente.first()
            )
        }
    }
}