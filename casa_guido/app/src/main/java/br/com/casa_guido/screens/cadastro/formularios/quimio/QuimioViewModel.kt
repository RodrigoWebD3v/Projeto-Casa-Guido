package br.com.casa_guido.screens.cadastro.formularios.radio

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.casa_guido.screens.Quimio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuimioViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<QuimioStateUi>(QuimioStateUi())
    val uiState = _uiState.asStateFlow()

    fun toggleDataPickerQuimioInicio() {
        _uiState.value =
            _uiState.value.copy(toggleDataPickerInicioQuimio = _uiState.value.toggleDataPickerInicioQuimio.not())
    }

    fun toggleDataPickerQuimioFim() {
        _uiState.value =
            _uiState.value.copy(toggleDataPickerFimQuimio = _uiState.value.toggleDataPickerFimQuimio.not())
    }

    fun onChangeQuimioInicio(data: String) {
        Log.i("QuimioViewModel", "onChangeQuimioEdicao Inicio: $data")

        _uiState.update {
            it.copy(
                quimioEdicao = it.quimioEdicao.copy(
                    dataInicio = data
                )
            )
        }
    }

    fun onChangeQuimioFim(data: String) {
        Log.i("QuimioViewModel", "onChangeQuimioEdicao Fim: $data")
        _uiState.update {
            it.copy(
                quimioEdicao = it.quimioEdicao.copy(
                    dataUltimaSessao = data
                )
            )
        }
    }

    fun addQuimio(quimio: Quimio) {
        val listaCirurgias = _uiState.value.listaQuimio.toMutableList()
        listaCirurgias.add(quimio)
        _uiState.value = _uiState.value.copy(listaQuimio = listaCirurgias)
    }

    fun RemoveIndex(index: Int) {
        val listaCirurgias = _uiState.value.listaQuimio.toMutableList()
        listaCirurgias.removeAt(index)
        _uiState.value = _uiState.value.copy(listaQuimio = listaCirurgias)
    }

    fun toggleList() {
        _uiState.update {
            it.copy(
                onVisibleList = !it.onVisibleList
            )
        }
    }

}