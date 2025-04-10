package br.com.casa_guido.screens.cadastro.formularios.cirurgia

import androidx.lifecycle.ViewModel
import br.com.casa_guido.screens.Cirurgia
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CirurgiaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<CirurgiaStateUi>(CirurgiaStateUi())
    val uiState = _uiState.asStateFlow()

    fun toggleDatePickerCirurgia() {
        _uiState.value =
            _uiState.value.copy(toggleDataPickerCirurgia = _uiState.value.toggleDataPickerCirurgia.not())
    }

    fun addCirurgia(cirurgiaEdicao: Cirurgia) {
        val listaCirurgias = _uiState.value.listaCirurgias.toMutableList()
        listaCirurgias.add(cirurgiaEdicao)
        _uiState.value = _uiState.value.copy(listaCirurgias = listaCirurgias)
    }

    fun RemoveIndex(index: Int) {
        val listaCirurgias = _uiState.value.listaCirurgias.toMutableList()
        listaCirurgias.removeAt(index)
        _uiState.value = _uiState.value.copy(listaCirurgias = listaCirurgias)
    }

}