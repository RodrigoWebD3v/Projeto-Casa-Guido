package br.com.casa_guido.screens.cadastro.formularios.radioterapia

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.casa_guido.models.Radio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RadioViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<RadioStateUi>(RadioStateUi())
    val uiState = _uiState.asStateFlow()

    fun toggleDataPickerRadioInicio() {
        _uiState.value =
            _uiState.value.copy(toggleDataPickerInicioRadio = _uiState.value.toggleDataPickerInicioRadio.not())
    }

    fun toggleDataPickerRadioFim() {
        _uiState.value =
            _uiState.value.copy(toggleDataPickerFimRadio = _uiState.value.toggleDataPickerFimRadio.not())
    }

    fun newRadio() {
        _uiState.update {
            it.copy(
                radioEdicao = Radio()
            )
        }
    }

    fun onChangeRadio(data: String) {
        _uiState.update {
            it.copy(
                radioEdicao = it.radioEdicao.copy(
                    dataInicio = data
                )
            )
        }
    }

    fun onChangeRadioFim(data: String) {
        Log.i("QuimioViewModel", "onChangeQuimioEdicao Fim: $data")
        _uiState.update {
            it.copy(
                radioEdicao = it.radioEdicao.copy(
                    dataUltimaSessao = data
                )
            )
        }
    }


    fun toggleList() {
        _uiState.update {
            it.copy(
                onVisibleList = !it.onVisibleList
            )
        }
    }

}