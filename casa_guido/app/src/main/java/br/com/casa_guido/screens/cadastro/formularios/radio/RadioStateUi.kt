package br.com.casa_guido.screens.cadastro.formularios.radio

import br.com.casa_guido.screens.Radio

data class RadioStateUi(
    val toggleDataPickerInicioRadio: Boolean = false,
    val toggleDataPickerFimRadio: Boolean = false,
    val listaRadio: List<Radio> = emptyList(),
    val radioEdicao: Radio = Radio(),
    val onVisibleList: Boolean = false,
)