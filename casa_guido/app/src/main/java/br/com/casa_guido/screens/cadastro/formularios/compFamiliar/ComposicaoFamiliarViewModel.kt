package br.com.casa_guido.screens.cadastro.formularios.compFamiliar

import android.util.Log
import androidx.lifecycle.ViewModel
import br.com.casa_guido.screens.ComposicaoFamiliar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ComposicaoFamiliarViewModel: ViewModel() {

    private val _uiState =
        MutableStateFlow<CompFamiliarStateUi>(CompFamiliarStateUi())
    val uiState = _uiState.asStateFlow()

    fun onChangeComFamiliaEdicao(composicaoFamiliar: ComposicaoFamiliar) {
        Log.d("teste", "onChangeComFamiliaEdicao: $composicaoFamiliar")
        _uiState.value = _uiState.value.copy(
            composicaoFamiliarEdicao = composicaoFamiliar
        )
    }

    fun toggleList() {
        _uiState.update {
            it.copy(
                onVisibleList = !_uiState.value.onVisibleList
            )
        }
    }
}