package br.com.casa_guido.screens.cadastro.formularios.observacao

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CadastroObservacaoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ObservacaoStateUi>(ObservacaoStateUi())
    val uiState = _uiState.asStateFlow()

    private val _isEdicao = MutableStateFlow<Boolean>(false)
    val isEdicao = _isEdicao.asStateFlow()

    private val _indexInEdicao = MutableStateFlow<Int>(0)
    val indexInEdicao = _indexInEdicao.asStateFlow()

    fun onChangeObservacaoEdicao(valor: String) {
        _uiState.value = _uiState.value.copy(
            observacaoEdicao = valor
        )
    }

    fun toggleList() {
        _uiState.update {
            it.copy(
                onVisibleList = it.onVisibleList.not()
            )
        }
    }

    fun onChangeIsEdicao(valor: Boolean) {
        _isEdicao.update {
            valor
        }
    }

    fun onChangeIndexInEdicao(index: Int) {
        _indexInEdicao.update {
            index
        }
    }
}