package br.com.casa_guido.screens.cadastro.formularios.radio

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.casa_guido.screens.Radio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RadioViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<RadioStateUi>(RadioStateUi())
    val uiState = _uiState.asStateFlow()

    fun toggleDataPickerQuimioInicio() {
        _uiState.value =
            _uiState.value.copy(toggleDataPickerInicioRadio = _uiState.value.toggleDataPickerInicioRadio.not())
    }

    fun toggleDataPickerQuimioFim() {
        _uiState.value =
            _uiState.value.copy(toggleDataPickerFimRadio = _uiState.value.toggleDataPickerFimRadio.not())
    }

    fun onChangeQuimioInicio(data: String) {
        Log.i("QuimioViewModel", "onChangeQuimioEdicao Inicio: $data")

        _uiState.update {
            it.copy(
                radioEdicao = it.radioEdicao.copy(
                    dataInicio = data
                )
            )
        }
    }

    fun onChangeQuimioFim(data: String) {
        Log.i("QuimioViewModel", "onChangeQuimioEdicao Fim: $data")
        _uiState.update {
            it.copy(
                radioEdicao = it.radioEdicao.copy(
                    dataUltimaSessao = data
                )
            )
        }
    }

    fun addQuimio(radio: Radio) {
        val listaCirurgias = _uiState.value.listaRadio.toMutableList()
        listaCirurgias.add(radio)
        _uiState.value = _uiState.value.copy(listaRadio = listaCirurgias)
    }

    fun RemoveIndex(index: Int) {
        val listaCirurgias = _uiState.value.listaRadio.toMutableList()
        listaCirurgias.removeAt(index)
        _uiState.value = _uiState.value.copy(listaRadio = listaCirurgias)
    }

    fun toggleList() {
        _uiState.update {
            it.copy(
                onVisibleList = !it.onVisibleList
            )
        }
    }

}