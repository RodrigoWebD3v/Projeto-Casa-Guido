package br.com.casa_guido.screens.cadastro.formularios.cirurgia

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.casa_guido.screens.Cirurgia
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CirurgiaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<CirurgiaStateUi>(CirurgiaStateUi())
    val uiState = _uiState.asStateFlow()

    fun toggleDatePickerCirurgia() {
        _uiState.value =
            _uiState.value.copy(toggleDataPickerCirurgia = _uiState.value.toggleDataPickerCirurgia.not())
    }

    fun onChangeCirurgiaEdicao(cirurgia: Cirurgia) {
        _uiState.value = _uiState.value.copy(
            cirurgiaEdicao = cirurgia
        )
    }

    fun toggleList() {
        _uiState.update {
            it.copy(
                onVisibleList = it.onVisibleList.not()
            )
        }
    }


}